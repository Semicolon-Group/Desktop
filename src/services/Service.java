package services;

import java.sql.Connection;
import util.DataSource;

public abstract class Service{
    
    protected final Connection CONNECTION;
    
    protected Service(){
        CONNECTION = DataSource.getInstance().getConnection();
    }
}
