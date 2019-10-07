package com.burger.controllers;

import com.burger.models.Command;
import com.burger.services.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path ="/command")
public class CommandController {

    @Autowired
    private CommandService commandService;

    @GetMapping("/")
    @ResponseBody
    public List<Command> getAll() {
        return commandService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Command getOne(@PathVariable("id") int id) {

        return commandService.getOne(id);
    }

    @GetMapping("/done/{done}")
    @ResponseBody
    public List<Command> getByDone(@PathVariable("done") int done) {

        return commandService.getByDone(done);
    }

    @PostMapping("/")
    @ResponseBody
    public Command create(@RequestBody Command command) {

        return commandService.save(command);
    }

    @PutMapping(value = "/")
    @ResponseBody
    public Command update(@RequestBody Command command) {
        return commandService.save(command);
    }

    @DeleteMapping(path="/{id}")
    @ResponseBody
    public void delete(@PathVariable int id) {
        commandService.deleteById(id);
    }
}
