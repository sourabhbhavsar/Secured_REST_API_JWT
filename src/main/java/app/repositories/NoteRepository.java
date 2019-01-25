package app.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import app.model.Note;

public interface NoteRepository extends PagingAndSortingRepository<Note, Long>{

}
