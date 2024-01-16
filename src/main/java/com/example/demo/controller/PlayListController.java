package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.Playlist;
import com.example.demo.entities.Song;
import com.example.demo.services.PlayListService;
import com.example.demo.services.SongService;

@Controller
public class PlayListController {

	@Autowired
	SongService songService;

	@Autowired
	PlayListService playlistservice;

	@GetMapping("/createPlayList")
	public String createPlayList(Model model) {
		List<Song> songList = songService.fetchAllSongs();
		model.addAttribute("songs", songList);
		return "createPlayList";
	}

	@PostMapping("/addPlayList")
	public String addPlayList(@ModelAttribute Playlist playlist) {

		playlistservice.addPlayList(playlist);

		List<Song> songList = playlist.getSong();

		for (Song s : songList) {
			s.getPlaylist().add(playlist);
			songService.updateSong(s);

		}

		return "adminHome";
	}
	
	@GetMapping("/viewPlayList")
	public String viewPlayList(Model model) {
		List<Playlist> allPlaylist=playlistservice.fetchAllPlaylist();
		model.addAttribute("allPlaylist", allPlaylist);
		return "displayPlayList";
	}
}
