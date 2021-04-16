package gr.codehub.pfizer.team1.resource;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResult<T> {

    private T data;
    private int code;
    private String description;
}