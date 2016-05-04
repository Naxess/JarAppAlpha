package com.com220.sli.jarappalpha;

import java.io.File;

public class Memory
{
    private String memoryName;
    private String memoryCreationDate;
    private String memoryUnlockDate;
    private String memoryDescription;
    private String memoryAddress;
    //private Location memoryLocation;  //REMOVED FROM APP.

    private int id;
    private int jarId;
    private String filePath;
    private File file;

    public Memory()
    {

    }
    public Memory(int id, String name, String address)
    {
        /*
        this.memoryName = memoryName;
        this.memoryCreationDate = memoryCreationDate;
        this.memoryUnlockDate = memoryUnlockDate;
        this.memoryDescription = memoryDescription;
        */
        this.id = id;
        this.memoryName = name;
        this.memoryAddress = address;
    }

    //GET METHODS
    public String getMemoryName()
    {
        return memoryName;
    }
    public String getMemoryCreationDate()
    {
        return memoryCreationDate;
    }
    public String getMemoryUnlockDate()
    {
        return memoryUnlockDate;
    }
    public String getMemoryDescription()
    {
        return memoryDescription;
    }
    public String getMemoryAddress()
    {
        return memoryAddress;
    }
    public int getId()
    {
        return id;
    }
    public int getJarId()
    {
        return jarId;
    }

    //SET METHODS
    void setMemoryName(String newMemoryName)
    {
        this.memoryName = newMemoryName;
    }
    void setMemoryCreationDate(String newMemoryCreationDate)
    {
        this.memoryCreationDate = newMemoryCreationDate;
    }
    void setMemoryUnlockDate(String newMemoryUnlockDate)
    {
        this.memoryName = newMemoryUnlockDate;
    }
    void setMemoryDescription(String newMemoryDescription)
    {
        this.memoryName = newMemoryDescription;
    }
    void setMemoryAddress(String newMemoryAddress)
    {
        this.memoryAddress = newMemoryAddress;
    }
    void setId(int newId)
    {
        this.id = newId;
    }
    void setJarId(int newJarId)
    {
        this.jarId = newJarId;
    }
}
