package com.example.crud.util;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;
@Setter
@Getter
@ToString
public class PaginationUtil {

    @NotNull(message = "Current Page can not be Null")
    @NotBlank(message = "Current page can not be blank")
    private Integer currentPage;

    @NotNull(message = "Items per Pages can not be Null")
    @NotBlank(message = "Items per Pages can not be blank")
    private Integer itemsPerPage;

    @NotNull(message = "Sort By can not be Null")
    @NotBlank(message = "Sort By can not be blank")
    private String sortBy;

    @NotNull(message = "Direction can not be Null")
    @NotBlank(message = "Direction can not be blank")
    private String direction;

}