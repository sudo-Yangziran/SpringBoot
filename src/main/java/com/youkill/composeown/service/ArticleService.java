package com.youkill.composeown.service;


import com.youkill.composeown.dao.ArticlesDAO;
import com.youkill.composeown.dao.BlogUserDao;
import com.youkill.composeown.dao.FilesDAO;
import com.youkill.composeown.dao.InitsDAO;
import com.youkill.composeown.entity.Articles;
import com.youkill.composeown.entity.Files;
import com.youkill.composeown.entity.LeadingFront;
import com.youkill.composeown.entity.PageArticles;
import com.youkill.composeown.utils.CheckUpload;
import com.youkill.composeown.utils.FileEncryptByName;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.processing.FilerException;
import java.io.*;
import java.util.*;

@Service
public class ArticleService {
    /**
    * @Author: youkill
    * @Description: 上传功能给完善，需要跟数据库结合一下
    * @DateTime: 2023/10/18 16:10
    */
    @Autowired
    InitsDAO initsDAO;
    @Autowired
    FilesDAO fileDao;
    @Autowired
    ArticlesDAO articlesDAO;
    public List<Articles> list(Map<String,Integer> map){
        Integer page=map.get("page");
        Integer currentpage=map.get("currentpage");
        currentpage=page*(currentpage-1);
        return articlesDAO.list(page,currentpage);
    }
    public List<Articles> indexlist(){
        return articlesDAO.indexList(5,1);
    }
    public List<Articles> indexArticleList(LeadingFront leading){
        List<Articles> list=articlesDAO.ArticleList(leading);
        if (!indexlist().isEmpty()){
            Random random=new Random();
            for (Articles a:list
                 ) {
                a.setImg("http://127.0.0.1:9191/img/"+"风景 ("+random.nextInt(6)+").jpg");
                a.setContext(GetFileLocalhost(a.getId()).toString());
                a.setId("");
            }
        }
        return list;
    }
    /**
    * @Author: youkill
    * @Description: 获取文章具体内容
    * @DateTime: 2023/11/7 14:51
    */
    public Articles GetArticle(String name){
        Articles articles=articlesDAO.GetArticl(name);
        articles.setContext(GetFileLocalhost2(articles.getId()).toString());
        articles.setId("");
        return articles;
    }
    public StringBuilder GetFileLocalhost2(String name){
        String filePath = initsDAO.SelectSystemValue("SystemFile").getValue() + "\\" + name + ".md";
        String fileContent = readFileContent(filePath);
        return new StringBuilder(fileContent);
    }
    public String readFileContent(String fileName) {
        StringBuilder content = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }



    public StringBuilder GetFileLocalhost(String name){
        String filePath = initsDAO.SelectSystemValue("SystemFile").getValue() + "\\" + name + ".md";
        String first50Characters = readFirst50CharactersFromFile(filePath);
        return new StringBuilder(first50Characters);
    }
    public String readFirst50CharactersFromFile(String fileName) {
        StringBuilder content = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
                if (content.length() >= 300) {
                    break;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString().substring(0, Math.min(300, content.length()))+"……";
    }
    public String Upload(MultipartFile multipartFile){
        String path=initsDAO.SelectSystemValue("SystemFile").getValue();
        if (path!=null) {
            try {
                String filename = multipartFile.getOriginalFilename();
                File tarFile = new File(path+GetUploadName());
                multipartFile.transferTo(tarFile);


            }catch (Exception e){
                e.printStackTrace();;
            }
        }
        return "";
    }
    public String GetUploadName(){
        String EncryptName=FileEncryptByName.generateUniqueEncryptedFilename();
        return EncryptName;
    }
    public String UploadPhotos(MultipartFile multipartFile) {
        String pathInformation = "";

        try {
            String basePath = initsDAO.SelectSystemValue("SystemImg").getValue();

            if (basePath != null) {
                String filename = multipartFile.getOriginalFilename();
                String extension = CheckUpload.photograph(filename);

                if (StringUtils.isEmpty(extension)) {
                    return null;
                }

                File basePathDir = new File(basePath);
                if (!basePathDir.exists()) {
                    if (!basePathDir.mkdirs()) {
                        throw new Exception("Failed to create base directory");
                    }
                }

                File file = new File(basePathDir, GetUploadName() + extension);
                String realPath = file.getCanonicalPath();
                multipartFile.transferTo(file);
                pathInformation = "http://127.0.0.1:9191/img/"+file.getName();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pathInformation;
    }

    /**
    * @Author: youkill
    * @Description: 在这里准备一下数据库，特别要注意状态，算了状态转成单个表，需要两个以及以上的表
     *    create table articles(id int,title varchar(100),createOwner id,create_time begindate,last_save_time begindate
     *    release begindate,status int);
     *
     * 下面是关于文件存储的一个映射，方便显示
     *    create table files(ids varchar(255),realname varchar(255))
     *CREATE TABLE articles (
     *     id INT PRIMARY KEY, -- 文章ID，主键
     *     title VARCHAR(100), -- 文章标题
     *     createOwner_id INT, -- 创建者的ID
     *     create_time DATE, -- 创建时间
     *     last_save_time DATETIME, -- 最后保存时间
     *     release_date DATE, -- 发布日期
     *     status INT -- 文章状态（你可以根据具体需求定义不同的状态值）
     * );
     *
     *
    * @DateTime: '2023/10/24 17:07
    */
    public boolean save_article(String filename){
        String path=initsDAO.SelectSystemValue("SystemFile").getValue()+"\\"+filename+".md";
        try {
            File directory = new File(path);
            if (!directory.exists()){
                directory.createNewFile();
            }
        }catch (Exception E){
            E.printStackTrace();
        }
        return true;
    }
    public boolean write_article(String filename,String text) {
        filename=articlesDAO.GetFileName(filename);
        String path=initsDAO.SelectSystemValue("SystemFile").getValue()+"\\"+filename+".md";
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(text);
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean new_articles(String name,String userid){
        TimeZone time = TimeZone.getTimeZone("Etc/GMT-8");  //转换为中国时区
        TimeZone.setDefault(time);
        if (name!=null||!name.equals("")){
            for (int i = 0; i < 10; i++) {
                Files file=new Files(name,GetUploadName());
                if(fileDao.insertFiles(file)==1){
                    Articles articles=new Articles();
                    articles.setStatus(0);
                    articles.setId(file.getRealname());
                    articles.setTitle(name);
                    articles.setCreateTime(new Date());
                    articles.setReleaseDate(new Date());
                    articles.setLastSaveTime(new Date());
                    articles.setCreateownerId(userid);
                    articlesDAO.CreateArticle(articles);
                    save_article(articles.getId());
                    return true;
                }
            }
        }
        return false;
    }
    public boolean re_articles(Files files){
        if (fileDao.updateBySao(files)==0){
            return false;
        }
        return true;
    }
    /**
    * @Author: youkill
    * @Description: 改变当前的state的状态，也就是文章是否发布
    * @DateTime: 2023/11/13 9:51
    */
    public int notState(String title){
        System.out.println(title);
        return articlesDAO.notArticleState(title);
    }
    public int count(){
        return articlesDAO.count();
    }
}
