
package dao;

import Ent.Grant;
import Ent.GrantUser;
import java.util.List;
import javax.ejb.Local;

@Local
public interface GDAO {
    public List<GrantUser> getGrantDetails(String semcode);
    
    public List<Grant> getAllGrants();
    
    
    public Grant getGrantBySemestr();
    public void SetGrant(int id_user, int id_grant, boolean status);
}
