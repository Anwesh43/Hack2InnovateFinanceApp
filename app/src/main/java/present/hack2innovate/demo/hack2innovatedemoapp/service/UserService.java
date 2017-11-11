package present.hack2innovate.demo.hack2innovatedemoapp.service;


import java.util.List;

import present.hack2innovate.demo.hack2innovatedemoapp.models.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by anweshmishra on 11/11/17.
 */

public interface UserService {
    @GET("/users")
    Call<List<User>> getUsers();
}
