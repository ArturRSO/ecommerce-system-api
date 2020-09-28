package ecommerce.system.api.repositories;

import ecommerce.system.api.exceptions.EmptySearchException;
import ecommerce.system.api.models.UserOptionModel;

import java.util.List;

public interface IUserOptionRepository {

    List<UserOptionModel> getUserOptionsByRoleId(int roleId) throws EmptySearchException;
}
