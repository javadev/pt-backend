package com.github.pt.admin.user;

import java.util.List;

class UserWorkoutRequestDTO {
    String name;
    UserWarmupWorkoutItemRequestDTO warmup;
    List<UserWorkoutItemRequestDTO> items;    
}
