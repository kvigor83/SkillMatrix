package by.kastsiuchenka.skillmatrix.service;



public interface SkillService {

    void update(String id,String data,String xlsFile) throws ServiceException;
    void create(String id,String data,String xlsFile) throws ServiceException;
    void delete(String id,String xlsFile) throws ServiceException;
    void createJSON(String xlsFile, String jsonFile) throws ServiceException;

}
