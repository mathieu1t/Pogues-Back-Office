package fr.insee.pogues.metadata.service;

import fr.insee.pogues.metadata.model.ColecticaItem;
import fr.insee.pogues.metadata.model.ColecticaItemRefList;
import fr.insee.pogues.search.model.Group;

import java.util.List;

public interface MetadataService {

    ColecticaItem getItem(String id) throws Exception;
    ColecticaItemRefList getChildrenRef(String id) throws Exception;
    List<ColecticaItem> getItems(ColecticaItemRefList refs) throws Exception;
    Group getGroup(String id) throws Exception;
    List<String> getGroupIds() throws Exception;
    String getDDIDocument(String id) throws Exception;
}