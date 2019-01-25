package app.repositories;
import org.springframework.data.repository.CrudRepository;

import app.model.UserInfo;

public interface UserInfoRepository extends CrudRepository<UserInfo, String> {

}