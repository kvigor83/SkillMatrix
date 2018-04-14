package by.kastsiuchenka.skillmatrix.service;



public interface SkillService {

    void update(long id) throws ServiceException;
    void create(long id) throws ServiceException;
    void delete(long id) throws ServiceException;
    void createJSON(String xlsFile, String jsonFile) throws ServiceException;

}
