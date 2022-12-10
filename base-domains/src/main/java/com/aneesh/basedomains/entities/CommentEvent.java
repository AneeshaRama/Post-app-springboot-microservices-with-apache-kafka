package com.aneesh.basedomains.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentEvent {

    private String message;

    private long commentId;

    private String commentContent;

    private long postId;

}
