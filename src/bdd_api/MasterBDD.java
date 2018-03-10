package bdd_api;
public class MasterBDD 
{
    private TSQLQuery query;
    
    public MasterBDD()
    {
        query = new TSQLQuery();
    }

    public TSQLQuery getQuery() {
        return query;
    }

    public void setQuery(TSQLQuery query) {
        this.query = query;
    }
    
    //Login
    public boolean userLogIn()
    {
        return false;
    }
    
    //LogOut
    public boolean userLogOut()
    {
        return false;
    }
    
    //Register
    public boolean userRegister()
    {
        return false;
    }
    
}
