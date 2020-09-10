package com.kyx.blog.service;

public interface IndexService {
    Long findArticleCounts();

    int findLabelCounts();

    int findGuestCounts();

}
