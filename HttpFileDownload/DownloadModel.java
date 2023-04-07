package com.company.HttpFileDownload;
import java.net.MalformedURLException;

public interface DownloadModel {
    public void fileDownload() throws MalformedURLException;
    public void baseDir();
    public void getDir(String dir);
}
