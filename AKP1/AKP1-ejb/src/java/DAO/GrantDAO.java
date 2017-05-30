
package DAO;

import Ent.Grant;
import java.util.List;
import javax.ejb.Local;

@Local
public interface GrantDAO {
    public void GrantCalculate();
    public List<Grant> getAllGrants();
}
