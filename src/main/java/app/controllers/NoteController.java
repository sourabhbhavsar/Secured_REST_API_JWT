package app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import app.services.NoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import app.model.Note;

@Api(value="Note")
@RestController
public class NoteController {

	public static int NOTES_PER_PAGE = 10;
	@Autowired
	private NoteService noteService;

	@ApiOperation(value="Getting all the notes in paginated form.")
	@RequestMapping(value = "/notes", method = RequestMethod.GET,  params = {"page"})
	public List<Note> getAllNotesPaginated( @RequestParam("page") int pageNum)
	{
		Pageable pageable = PageRequest.of(pageNum, NOTES_PER_PAGE,
				Sort.by("createdDate").descending());
		Page<Note> pages = noteService.listAllPaginated(pageable);

		return pages.getContent();
	}

	@ApiOperation(value="Getting a specific note by id")
	@RequestMapping(value = "/notes{id}", method = RequestMethod.GET,  params = {"page"})
	public Note getNoteById(@PathVariable(name = "id")  Long id)
	{
		return noteService.getById(id);
	}

	
	
	@ApiOperation(value="Adding a note")
	@RequestMapping(value = "/notes", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public Note addNote(@RequestBody Note note) {
		return noteService.save(note);
	}


	@ApiOperation(value="Updating a note.")
	@RequestMapping(value = "/notes/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	@ResponseStatus(code = HttpStatus.OK)
	public Note updateNote(@RequestBody Note note, @PathVariable(name = "id")  Long id) {

		Note noteUpdated = null;

		noteUpdated  = noteService.update(note, id);

		return noteUpdated;
	}


	@ApiOperation(value="Deleting a note.")
	@RequestMapping(value = "/notes/{id}", method = RequestMethod.DELETE , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteIdea(@PathVariable(name = "id") Long id) {
		noteService.delete(id);
	}

}
