package com.example.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class PostResponse {

   private List<Postdto> posts;
   private Integer pageNumber;
   private Integer pageSize;
   private boolean lastPage;
   private Integer totalElements;

}
