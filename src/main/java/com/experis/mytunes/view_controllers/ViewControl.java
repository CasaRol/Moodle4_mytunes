package com.experis.mytunes.view_controllers;

import com.experis.mytunes.dataAccess.RandomDataHandler;
import com.experis.mytunes.dataAccess.SearchDataHandler;
import com.experis.mytunes.models.SearchResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

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
        ArrayList<SearchResult> searchResults = searchData.getSearch((search));
        if(searchResults.size() <= 0) {
            return "Error404";
        }
        model.addAttribute("result", searchResults.get(0));
        //model.addAttribute("result", search);
        return "search_result";

    }
}
