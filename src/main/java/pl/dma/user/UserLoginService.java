package pl.dma.user;

public class UserLoginService {
    private UserDAO userDAO;

    public UserLoginService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    private boolean userExists(UserLoginDTO dto){
//        return userDAO.getUsersList().stream()
//                .map(User::getName)
//                .anyMatch(u -> u.equals(dto.getName()));
        return userDAO.checkIfUSerExists(dto.getName());
    }

    public boolean login(UserLoginDTO dto) {
        if (userExists(dto)) {
            User user = new User(dto.getName(), dto.getPassword());
            return userDAO.getFromDB(user);
        }
        return false;
    }

/*
    public boolean login(UserLoginDTO dto) {
        userDAO.populateUserList();
        return userDAO.getUsersList().stream()
                .filter(u -> dto.getName().equals(u.getName()))
                .findAny()
                .map(u -> u.getPassword().equals(dto.getPassword()))
                .orElse(false);
    }

    public User getLoggedUser(UserLoginDTO dto){
        return userDAO.getUsersList().stream()
                .filter(u -> dto.getName().equals(u.getName()))
                .findFirst()
                .orElse(null);

    }
*/
}
