package com.humlib.controller

import com.humlib.model.Human
import com.humlib.model.HumansTags
import com.humlib.security.annotations.IsKid
import com.humlib.service.SearchHumansService
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/search")
@IsKid
class SearchHumansController(
    val searchHumansService: SearchHumansService
) {

    @GetMapping
    fun getAll(): List<Human> {
        return searchHumansService.getAllHumans()
    }

    @GetMapping("/contains_all")
    fun containsAll(
        @RequestParam pageNo: Int,
        @RequestParam pageSize: Int,
        @RequestBody humansTags: HumansTags
    ): List<Human> {
        return searchHumansService.searchContainsAllHumans(PageRequest.of(pageNo, pageSize), humansTags)
    }

    @GetMapping("/contains")
    fun contains(
        @RequestParam pageNo: Int,
        @RequestParam pageSize: Int,
        @RequestParam matchesAtLeast: Int?,
        @RequestBody humansTags: HumansTags
    ): List<Human> {
        return searchHumansService.searchContainsHumans(PageRequest.of(pageNo, pageSize), humansTags, matchesAtLeast)
    }
}