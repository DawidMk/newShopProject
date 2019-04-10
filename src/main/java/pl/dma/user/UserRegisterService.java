package pl.dma.user;

import pl.dma.exceptions.UserExistsException;

public class UserRegisterService {
    UserDAO userDAO;

    public UserRegisterService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    private boolean userExists(UserRegisterDTO dto){
//        return userDAO.getUsersList().stream()
//                .map(User::getName)
//                .anyMatch(u -> u.equals(dto.getName()));
        return userDAO.checkIfUSerExists(dto.getName());

    }

    public boolean register(UserRegisterDTO dto) {
        if (userExists(dto)) {
            return false;
//            throw new UserExistsException("user: " + dto.getName() + " już istnieje");
        } else {
            User user = new User(dto.getName(), dto.getPassword());
            userDAO.putIntoDB(user);
        }
        return true;
    }

}
