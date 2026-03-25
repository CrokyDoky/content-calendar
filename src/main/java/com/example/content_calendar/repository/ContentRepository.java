package com.example.content_calendar.repository;

import com.example.content_calendar.model.Content;
import org.springframework.data.repository.ListCrudRepository;

public interface ContentRepository extends ListCrudRepository<Content, Integer> {

}
