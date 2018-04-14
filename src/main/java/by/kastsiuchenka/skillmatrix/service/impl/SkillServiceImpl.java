package by.kastsiuchenka.skillmatrix.service.impl;


import by.kastsiuchenka.skillmatrix.parser.XLSParser;
import by.kastsiuchenka.skillmatrix.service.ServiceException;
import by.kastsiuchenka.skillmatrix.service.SkillService;

public class SkillServiceImpl implements SkillService {
    private static final SkillService INSTANCE = new SkillServiceImpl();


    public static SkillService getInstance() {
        return INSTANCE;
    }

    @Override
    public void createJSON(String xlsFile, String jsonFile) throws ServiceException {
        XLSParser parser = new XLSParser(xlsFile, jsonFile);
        parser.parseXLSToJSON();
    }


    @Override
    public void update(long id) throws ServiceException {

    }

    @Override
    public void create(long id) throws ServiceException {

    }

    @Override
    public void delete(long id) throws ServiceException {

    }
}
