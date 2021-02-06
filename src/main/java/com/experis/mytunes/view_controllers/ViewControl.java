package com.experis.mytunes.view_controllers;

import com.experis.mytunes.dataAccess.RandomDataHandler;
import com.experis.mytunes.dataAccess.SearchDataHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewControl {

    RandomDataHandler randData = new RandomDataHandler();
    SearchDataHandler searchData = new SearchDataHandler();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndex(Model model) {
        model.addAttribute("artists", randData.getRandomArtists());
        model.addAttribute("songs", randData.getRandomSongs());
        model.addAttribute("genres", randData.getRandomGenres());
        //returning html file index
        return "index";
    }

    @RequestMapping(value = "/search_song", method = RequestMethod.GET)
    public String getSearchedSong(Model model, @RequestParam(value="searchTerm") String search) {
        model.addAttribute("result", searchData.getSearch(search).get(0));
        //model.addAttribute("result", search);
        return "search_result";

    }
}
