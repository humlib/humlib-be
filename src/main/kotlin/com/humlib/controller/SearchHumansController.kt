package com.humlib.controller

import com.humlib.controller.dto.TagsDTO
import com.humlib.controller.dto.toDtos
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
    fun getAll() =
        searchHumansService
            .getAllHumans()
            .toDtos()

    @GetMapping("/contains_all")
    fun containsAll(
        @RequestParam pageNo: Int,
        @RequestParam pageSize: Int,
        @RequestBody tags: TagsDTO
    ) = searchHumansService
        .searchContainsAllHumans(PageRequest.of(pageNo, pageSize), tags.entity())
        .toDtos()

    @GetMapping("/contains")
    fun contains(
        @RequestParam pageNo: Int,
        @RequestParam pageSize: Int,
        @RequestParam matchesAtLeast: Int?,
        @RequestBody tags: TagsDTO
    ) = searchHumansService
        .searchContainsHumans(PageRequest.of(pageNo, pageSize), tags.entity(), matchesAtLeast)
        .toDtos()
}