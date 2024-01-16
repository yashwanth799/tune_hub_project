package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Playlist;
import com.example.demo.repositories.PlayListRepository;

@Service
public class PlayListServiceImplimentation implements PlayListService {

	@Autowired
	PlayListRepository repo;

	@Override
	public void addPlayList(Playlist playlist) {
		repo.save(playlist);

	}

	@Override
	public List<Playlist> fetchAllPlaylist() {
		
		return repo.findAll();
	}

}
