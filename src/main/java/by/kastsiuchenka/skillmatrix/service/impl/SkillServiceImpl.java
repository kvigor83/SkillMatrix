package by.kastsiuchenka.skillmatrix.service.impl;


import by.kastsiuchenka.skillmatrix.action.SkillAction;
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
    public void update(String id,String data,String xlsFile) throws ServiceException {

        SkillAction action = new SkillAction();
        action.updateSkill(id,data,xlsFile);
    }

    @Override
    public void create(String id,String data,String xlsFile) throws ServiceException {
        SkillAction action = new SkillAction();
        action.createSkill(id,data,xlsFile);
    }

    @Override
    public void delete(String id,String xlsFile) throws ServiceException {
        SkillAction action = new SkillAction();
        action.deleteSkill(id,xlsFile);
    }
}
