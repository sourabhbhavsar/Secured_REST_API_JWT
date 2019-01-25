package app.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import app.model.Note;
import app.repositories.NoteRepository;

@Service
public class NoteServiceImpl implements NoteService {

	private NoteRepository noteRepository;

	@Autowired
	public NoteServiceImpl(NoteRepository noteRepository) {
		this.setNoteRepository(noteRepository);
	}

	public List<Note> listAll() {
		List<Note> notes = new ArrayList<Note>();
		noteRepository.findAll().forEach(notes::add); 

		return notes;
	}

	public Note getById(Long id) {
		Note note =  null;
		note =  noteRepository.findById(id).get();
		return note;
	}

	public Note save(Note note) {
		Note savedNote = noteRepository.save(note);

		return savedNote;
	}

	public Note update(Note note, Long id) {
		Note noteToUpdate = getById(id);
		noteToUpdate.setContent(note.getContent());
		noteToUpdate.setCreatedDate(new Date());
		
		noteRepository.save(noteToUpdate);

		return noteToUpdate;
	}

	public void delete(Long id) {
		Note noteToDelete = getById(id);

		noteRepository.deleteById(id);	
	}

	public Page<Note> listAllPaginated(Pageable pageable) {
		return noteRepository.findAll(pageable);
	}

	public NoteRepository getNoteRepository() {
		return noteRepository;
	}

	public void setNoteRepository(NoteRepository noteRepository) {
		this.noteRepository = noteRepository;
	}

}
