package kz.bookcrossing.entity.dto;

import kz.bookcrossing.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private User user;
    private String token;
}
