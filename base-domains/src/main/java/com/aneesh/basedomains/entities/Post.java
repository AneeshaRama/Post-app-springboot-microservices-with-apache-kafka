package com.aneesh.basedomains.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Post {

    private long postId;

    private String postTitle;

    private String postContent;

}
