package ru.job4j.logic;

import org.apache.log4j.Logger;
import ru.job4j.persistent.DBStore;
import ru.job4j.persistent.Store;
import ru.job4j.persistent.User;

import java.util.List;

/**.
 * Task 9.2.1.
 * Class for realisation logic for this web api
 *
 * @author Anton Vasilyuk
 * @version 1.0.
 */

public class ValidateService {

    /**.
     * Is LOGGER for this class
     */
    private static final Logger LOG = Logger.getLogger(ValidateService.class);

    /**.
     * Instance for singleton ValidateService class
     */
    private static final ValidateService LINK = new ValidateService();

    /**.
     * Link for instance Store interface
     */
    private final Store store = DBStore.getInstance();

    /**.
     * Hidden constructor
     */
    private ValidateService() {
    }

    /**.
     * Method for getting instance single example for this class
     * @return instance
     */
    public static ValidateService getInstance() {
        return LINK;
    }

    /**.
     * Method for adding new user
     * @param user is new user
     * @return result operation
     */
    public boolean add(User user) {
        if (isCredentional(user)) {
            store.add(user);
            return true;
        }
        LOG.info(String.format("user %s_%s_%s isExist", user.getName(), user.getLogin(), user.getEmail()));
        return false;
    }

    /**.
     * Method for update user in storage
     * @param user is new user
     * @return result operation
     */
    public boolean update(User user) {
        if (store.needUpdate(user)) {
            store.update(user);
        }
        return false;
    }

    /**.
     * Method for delete user from storage
     * @param id is id user for deleting
     * @return result operation
     */
    public boolean delete(int id) {
        if (store.existID(id)) {
            store.delete(id);
            return true;
        } else {
            return false;
        }
    }

    /**.
     * Getter for storage
     * @return storage
     */
    public synchronized List<User> getListStorage() {
        return store.findByAll();
    }

    /**.
     * Method for checking authorisation
     * @param user for checking
     * @return result
     */
    public boolean isCredentional(User user) {
        return store.isCredentional(user);
    }

    /**.
     * Method for checking role the user
     * @param login is login for checking
     * @return true if user is admin
     */
    public boolean isAdmin(String login) {
        return store.isAdmin(login);
    }

    /**.
     * Getter for list of the countries
     * @return list countries
     */
    public List<String> getCountries() {
        return store.getCountries();
    }

    /**.
     * Getter for list of the cies for this country
     * @param country is counties for getter all cities
     * @return list of the cities
     */
    public List<String> getCity(String country) {
        return store.getCity(country);
    }

    /**.
     * Method for checking user for existing
     * @param login for user
     * @param password for user
     * @return true if exist
     */
    public boolean isExisting(String login, String password) {
        return (store.isExisting(login, password));
    }
}
