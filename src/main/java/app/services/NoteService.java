package app.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import app.model.Note;

public interface NoteService {
	List<Note> listAll();

	Note getById(Long id);

	Note save(Note note);
	Note update(Note note, Long id);

	void delete(Long id);

	Page<Note> listAllPaginated(Pageable pageable);

}
