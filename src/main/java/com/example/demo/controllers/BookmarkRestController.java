package com.example.demo.controllers;

import com.example.demo.dao.BookmarkRepository;
import com.example.demo.domain.Bookmark;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/{userId}/bookmarks")
public class BookmarkRestController {
    private final BookmarkRepository bookmarkRepository;

    private final static Logger logger = LoggerFactory.getLogger(BookmarkRestController.class);

    @Autowired
    BookmarkRestController(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    Collection<Bookmark> readBookmarks(@PathVariable String userId) {
        logger.debug("Invoked for userId : {}", userId);
        Collection<Bookmark> byAccountUsername = this.bookmarkRepository.findByAccountUsername(userId);
        logger.debug("Found {} Bookmarks for userId {}", byAccountUsername.size(), userId);
        return byAccountUsername;
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@PathVariable String userId, @RequestBody Bookmark input) {
        return null;

    }

    @RequestMapping(method = RequestMethod.GET, value = "/{bookmarkId}")
    Bookmark readBookmark(@PathVariable String userId, @PathVariable Long bookmarkId) {
        return this.bookmarkRepository.findOne(bookmarkId);
    }


}
