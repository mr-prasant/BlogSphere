# BlogSphere
BlogSphere is an interactive platform designed for sharing, discovering, and discussing diverse topics through blogs. Users can create and publish posts, engage with readers through comments, and build an online community.

BlogSphere is an innovative blogging platform that offers users a comprehensive space for sharing, discovering, and discussing a wide range of topics. Designed to foster a sense of community and interaction, BlogSphere allows users to create blog posts on any topic, making it a hub for diverse and engaging content. The platform is equipped with several features to enhance both user engagement and content discoverability:

User Profiles and Personalization: Every user has a personal profile where they can showcase their bio, interests, and a list of published blogs. This helps readers identify and connect with writers they relate to, fostering a personalized experience and deeper engagement.

Content Creation and Publishing: BlogSphere provides an intuitive content editor that supports multimedia elements, allowing users to include images, videos, and formatted text in their blogs. The platform also encourages originality and quality by offering guidelines and tips for creating impactful content.

Categorization and Search Functionality: To make navigation easier, BlogSphere categorizes blogs into various topics such as technology, lifestyle, travel, education, health, and more. This allows readers to browse content based on their interests. The advanced search functionality further enables users to find specific articles or authors easily, ensuring that content remains accessible.

Commenting and Community Interaction: BlogSphere places a strong emphasis on community engagement. Readers can leave comments on blog posts, enabling authors to receive feedback and fostering discussions. The platform's notification system keeps users updated on new comments, likes, and responses, encouraging real-time interaction.

Engagement Metrics: Authors can track the performance of their posts through metrics such as views, likes, comments, and shares. These analytics provide insights into audience preferences, helping authors understand what content resonates most with readers.

Like and Share Features: Readers can show appreciation for blog posts through likes, and share posts across social media platforms, expanding the reach of content and helping authors gain visibility.

In summary, BlogSphere aims to create a collaborative, informative, and enjoyable environment for both authors and readers. It provides the tools necessary for authors to share their voices with a broad audience, while also giving readers a seamless way to explore, interact, and discover new content. With its rich features and community-driven approach, BlogSphere stands out as a unique platform for both casual readers and dedicated bloggers.

# HOW TO USE DATABASE

# Tables in Oracle SQL

SQL> select * from tab;

TNAME                          TABTYPE  CLUSTERID
------------------------------ ------- ----------
BLOGS                          TABLE
COMMENTS                       TABLE
LIKES                          TABLE
USERS                          TABLE

SQL> desc users;
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 USEREMAIL                                 NOT NULL VARCHAR2(60)
 USERNAME                                           VARCHAR2(30)
 CONTACT                                            NUMBER(12)
 PASSWORD                                           VARCHAR2(20)

SQL> desc blogs;
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 BLOGID                                    NOT NULL VARCHAR2(20)
 USEREMAIL                                          VARCHAR2(30)
 TITLE                                              VARCHAR2(100)
 CONTENT                                            VARCHAR2(4000)
 TIME                                               DATE
 CATEGORY                                           VARCHAR2(20)
 USERNAME                                           VARCHAR2(30)
 LIKE_COUNT                                         NUMBER
 COMMENT_COUNT                                      NUMBER
 VIEWS                                              NUMBER
 MILLISECOND                                        NUMBER

SQL> desc comments;
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 BLOGID                                             VARCHAR2(20)
 USERID                                             VARCHAR2(30)
 CONTENT                                            VARCHAR2(1000)
 TIME                                               DATE
 MILLISECOND                                        NUMBER

SQL> desc likes;
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 BLOGID                                             VARCHAR2(20)
 USERID                                             VARCHAR2(30)

# Primary key
CONSTRAINT_NAME                TABLE_NAME           C
------------------------------ -------------------- -
BLOGS_BLOGID_PK                BLOGS                P
SYS_C007012                    USERS                P

# Foreign Key
CONSTRAINT_NAME                TABLE_NAME           C
------------------------------ -------------------- -
COMMENTS_USERID_FK             COMMENTS             R
LIKES_USERID_FK                LIKES                R
BLOGS_USEREMAIL_FK             BLOGS                R
COMMENTS_BLOGID_FK             COMMENTS             R
LIKES_BLOGID_FK                LIKES                R

# All triggers

TRIGGER_NAME                   TRIGGERING_EVENT     TABLE_NAME           STATUS
------------------------------ -------------------- -------------------- ----------
UPDATE_LIKE_COUNT_AFTER_DELETE DELETE               LIKES                ENABLED
UPD_CMNT_CNT_DEL               DELETE               COMMENTS             ENABLED
UPD_CMNT_CNT_INS               INSERT               COMMENTS             ENABLED
UPDATE_LIKE_COUNT_AFTER_INSERT INSERT               LIKES                ENABLED

# SQL Code
-- Creating USERS table
CREATE TABLE USERS (
    USEREMAIL VARCHAR2(60) NOT NULL,
    USERNAME VARCHAR2(30),
    CONTACT NUMBER(12),
    PASSWORD VARCHAR2(20),
    CONSTRAINT pk_users PRIMARY KEY (USEREMAIL)
);

-- Creating BLOGS table
CREATE TABLE BLOGS (
    BLOGID VARCHAR2(20) NOT NULL,
    USEREMAIL VARCHAR2(30),
    TITLE VARCHAR2(100),
    CONTENT VARCHAR2(4000),
    TIME DATE,
    CATEGORY VARCHAR2(20),
    USERNAME VARCHAR2(30),
    LIKE_COUNT NUMBER,
    COMMENT_COUNT NUMBER,
    VIEWS NUMBER,
    MILLISECOND NUMBER,
    CONSTRAINT pk_blogs PRIMARY KEY (BLOGID),
    CONSTRAINT fk_blogs_users FOREIGN KEY (USEREMAIL) REFERENCES USERS(USEREMAIL)
);

-- Creating COMMENTS table
CREATE TABLE COMMENTS (
    BLOGID VARCHAR2(20),
    USERID VARCHAR2(30),
    CONTENT VARCHAR2(1000),
    TIME DATE,
    MILLISECOND NUMBER,
    CONSTRAINT fk_comments_blogs FOREIGN KEY (BLOGID) REFERENCES BLOGS(BLOGID),
    CONSTRAINT fk_comments_users FOREIGN KEY (USERID) REFERENCES USERS(USEREMAIL)
);

-- Creating LIKES table
CREATE TABLE LIKES (
    BLOGID VARCHAR2(20),
    USERID VARCHAR2(30),
    CONSTRAINT fk_likes_blogs FOREIGN KEY (BLOGID) REFERENCES BLOGS(BLOGID),
    CONSTRAINT fk_likes_users FOREIGN KEY (USERID) REFERENCES USERS(USEREMAIL)
);

-- Creating primary key for USERS table
ALTER TABLE USERS
ADD CONSTRAINT pk_users PRIMARY KEY (USEREMAIL);

-- Creating primary key for BLOGS table
ALTER TABLE BLOGS
ADD CONSTRAINT pk_blogs PRIMARY KEY (BLOGID);

-- Creating foreign key in BLOGS referencing USERS
ALTER TABLE BLOGS
ADD CONSTRAINT fk_blogs_users FOREIGN KEY (USEREMAIL) REFERENCES USERS(USEREMAIL);

-- Creating foreign key in COMMENTS referencing BLOGS
ALTER TABLE COMMENTS
ADD CONSTRAINT fk_comments_blogs FOREIGN KEY (BLOGID) REFERENCES BLOGS(BLOGID);

-- Creating foreign key in COMMENTS referencing USERS
ALTER TABLE COMMENTS
ADD CONSTRAINT fk_comments_users FOREIGN KEY (USERID) REFERENCES USERS(USEREMAIL);

-- Creating foreign key in LIKES referencing BLOGS
ALTER TABLE LIKES
ADD CONSTRAINT fk_likes_blogs FOREIGN KEY (BLOGID) REFERENCES BLOGS(BLOGID);

-- Creating foreign key in LIKES referencing USERS
ALTER TABLE LIKES
ADD CONSTRAINT fk_likes_users FOREIGN KEY (USERID) REFERENCES USERS(USEREMAIL);
