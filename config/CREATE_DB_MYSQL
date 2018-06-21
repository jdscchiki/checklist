DROP DATABASE IF EXISTS CHECK_LIST_DB;
CREATE DATABASE CHECK_LIST_DB;
USE CHECK_LIST_DB;

--
-- state
--
create table state(
    id int not null primary key ,
    name varchar(20)
);

--
-- result_value
--
create table result_value(
    id int not null primary key ,
    name varchar(20)
);

--
-- user
--
create table app_user(
    id int not null primary key AUTO_INCREMENT,
    nick varchar(32) not null,
    password varchar(256) not null,
    firts_name varchar(128) ,
    last_name varchar(128) ,
    state int not null
);
Alter Table app_user
Add FOREIGN KEY (state)
References state (id);
Alter Table app_user
Add Constraint nick_unique
Unique (nick);


--
-- project
--
create table project(
    id int not null primary key AUTO_INCREMENT,
    name varchar(256) not null,
    state int not null
);
Alter Table project
Add FOREIGN KEY (state)
References state (id);

--
-- project_version
--
create table project_version(
    id int not null primary key AUTO_INCREMENT,
    value int not null,
    state int not null,
    project int not null
);
Alter Table project_version
Add FOREIGN KEY (state)
References state (id);
Alter Table project_version
Add FOREIGN KEY (project)
References project (id);

--
-- project_subversion
--
create table project_subversion(
    id int not null primary key AUTO_INCREMENT,
    value int not null,
    state int not null,
    project_version int not null
);
Alter Table project_subversion
Add FOREIGN KEY (state)
References state (id);
Alter Table project_subversion
Add FOREIGN KEY (project_version)
References project_version (id);

--
-- project_revision
--
create table project_revision(
    id int not null primary key AUTO_INCREMENT,
    value int not null,
    state int not null,
    project_subversion int not null
);
Alter Table project_revision
Add FOREIGN KEY (state)
References state (id);
Alter Table project_revision
Add FOREIGN KEY (project_subversion)
References project_subversion (id);

--
-- checklist
--
create table checklist(
    id int not null primary key AUTO_INCREMENT,
    name varchar(256) not null,
    state int not null
);
Alter Table checklist
Add FOREIGN KEY (state)
References state (id);

--
-- checklist_version
--
create table checklist_version(
    id int not null primary key AUTO_INCREMENT,
    value int not null,
    state int not null,
    checklist int not null
);
Alter Table checklist_version
Add FOREIGN KEY (state)
References state (id);
Alter Table checklist_version
Add FOREIGN KEY (checklist)
References checklist (id);

--
-- checklist_item
--
create table checklist_item(
    id int not null primary key AUTO_INCREMENT,
    value varchar(2048) not null,
    state int not null,
    checklist_version int,
    checklist_parent_item int 
);
Alter Table checklist_item
Add FOREIGN KEY (state)
References state (id);
Alter Table checklist_item
Add FOREIGN KEY (checklist_version)
References checklist_version (id);
Alter Table checklist_item
Add FOREIGN KEY (checklist_parent_item)
References checklist_item (id);

--
-- revision_result
--
create table revision_result(
    id int not null primary key AUTO_INCREMENT,
    comment varchar(1024) ,
    creation_date timestamp not null,
    executor int not null,
    state int not null,
    checklist_version int not null,
    project_revision int not null
);
Alter Table revision_result
Add FOREIGN KEY (state)
References state (id);
Alter Table revision_result
Add FOREIGN KEY (checklist_version)
References checklist_version (id);
Alter Table revision_result
Add FOREIGN KEY (project_revision)
References checklist_item (id);
Alter Table revision_result
Add FOREIGN KEY (executor)
References app_user (id);


--
-- revision_result_item
--
create table revision_result_item(
    id int not null primary key AUTO_INCREMENT,
    value int ,
    comment varchar(1024),
    creation_date timestamp not null,
    state int not null,
    revision_result int not null,
    checklist_item int not null
);
Alter Table revision_result_item
Add FOREIGN KEY (state)
References state (id);
Alter Table revision_result_item
Add FOREIGN KEY (value)
References result_value (id);
Alter Table revision_result_item
Add FOREIGN KEY (revision_result)
References revision_result (id);
Alter Table revision_result_item
Add FOREIGN KEY (checklist_item)
References checklist_item (id);

--
-- checklist_owner
--
create table checklist_owner(
    id int not null primary key AUTO_INCREMENT,
    creation_date timestamp not null,
    state int not null,
    checklist int not null,
    app_user int not null
);
Alter Table checklist_owner
Add FOREIGN KEY (state)
References state (id);
Alter Table checklist_owner
Add FOREIGN KEY (checklist)
References checklist (id);
Alter Table checklist_owner
Add FOREIGN KEY (app_user)
References app_user (id);

--
-- project_owner
--
create table project_owner(
    id int not null primary key AUTO_INCREMENT,
    creation_date timestamp not null,
    state int not null,
    project int not null,
    app_user int not null
);
Alter Table project_owner
Add FOREIGN KEY (state)
References state (id);
Alter Table project_owner
Add FOREIGN KEY (project)
References project (id);
Alter Table project_owner
Add FOREIGN KEY (app_user)
References app_user (id);

--
-- Config_db
--

INSERT INTO STATE (ID, NAME) VALUES (1,'ACTIVE'),(0,'INACTIVE');
INSERT INTO RESULT_VALUE (ID, NAME) VALUES (1, 'APROVED'),(0, 'REPROBATE');

--
-- example insertion
--

INSERT INTO APP_USER (NICK,PASSWORD, FIRTS_NAME, LAST_NAME, STATE) VALUES ('admin', 'admin', 'admin', 'admin', 1);

INSERT INTO PROJECT (ID ,NAME, STATE) VALUES (1, 'example project #1', 1);
INSERT INTO PROJECT_VERSION (ID, VALUE, STATE, PROJECT) VALUES (1, 1, 1, 1);
INSERT INTO PROJECT_SUBVERSION (ID, VALUE, STATE, PROJECT_VERSION) VALUES (1, 1, 1, 1);
INSERT INTO PROJECT_REVISION (ID, VALUE, STATE, PROJECT_SUBVERSION) VALUES (1, 1, 1, 1);
INSERT INTO PROJECT_REVISION (ID, VALUE, STATE, PROJECT_SUBVERSION) VALUES (2, 2, 1, 1);
INSERT INTO PROJECT_OWNER (ID, CREATION_DATE, STATE, PROJECT, APP_USER) VALUES (1,'2018-04-30 09:18:09', 1, 1, 1);

INSERT INTO PROJECT (ID ,NAME, STATE) VALUES (2, 'example project #2', 1);
INSERT INTO PROJECT_VERSION (ID, VALUE, STATE, PROJECT) VALUES (2, 1, 1, 2);
INSERT INTO PROJECT_SUBVERSION (ID, VALUE, STATE, PROJECT_VERSION) VALUES (2, 1, 1, 2);
INSERT INTO PROJECT_REVISION (ID, VALUE, STATE, PROJECT_SUBVERSION) VALUES (3, 1, 1, 2);
INSERT INTO PROJECT_REVISION (ID, VALUE, STATE, PROJECT_SUBVERSION) VALUES (4, 1, 1, 2);
INSERT INTO PROJECT_OWNER (ID, CREATION_DATE, STATE, PROJECT, APP_USER) VALUES (2,'2018-04-30 09:18:09', 1, 2, 1);

INSERT INTO CHECKLIST (ID,NAME, STATE) VALUES (1,'example checklist #1', 1);
INSERT INTO CHECKLIST_VERSION (ID, VALUE, STATE, CHECKLIST) VALUES (1, 1, 1, 1);
INSERT INTO CHECKLIST_ITEM (ID, VALUE, STATE, CHECKLIST_VERSION, CHECKLIST_PARENT_ITEM) VALUES (1, 'example checklist item #1', 1, 1, NULL);
INSERT INTO CHECKLIST_ITEM (ID, VALUE, STATE, CHECKLIST_VERSION, CHECKLIST_PARENT_ITEM) VALUES (2, 'example checklist item #2', 1, 1, NULL);
INSERT INTO CHECKLIST_ITEM (ID, VALUE, STATE, CHECKLIST_VERSION, CHECKLIST_PARENT_ITEM) VALUES (3, 'example checklist item #3', 1, NULL, 1);
INSERT INTO CHECKLIST_OWNER (ID, CREATION_DATE, STATE, CHECKLIST, APP_USER) VALUES (1, '2018-04-30 10:18:09', 1, 1, 1);

INSERT INTO CHECKLIST (ID,NAME, STATE) VALUES (2,'example checklist #2', 1);
INSERT INTO CHECKLIST_VERSION (ID, VALUE, STATE, CHECKLIST) VALUES (2, 1, 1, 2);
INSERT INTO CHECKLIST_ITEM (ID, VALUE, STATE, CHECKLIST_VERSION, CHECKLIST_PARENT_ITEM) VALUES (4, 'example checklist item #4', 1, 2, NULL);
INSERT INTO CHECKLIST_ITEM (ID, VALUE, STATE, CHECKLIST_VERSION, CHECKLIST_PARENT_ITEM) VALUES (5, 'example checklist item #5', 1, 2, NULL);
INSERT INTO CHECKLIST_ITEM (ID, VALUE, STATE, CHECKLIST_VERSION, CHECKLIST_PARENT_ITEM) VALUES (6, 'example checklist item #6', 1, NULL, 1);
INSERT INTO CHECKLIST_OWNER (ID, CREATION_DATE, STATE, CHECKLIST, APP_USER) VALUES (2, '2018-04-30 10:18:09', 1, 2, 1);

INSERT INTO REVISION_RESULT (ID, COMMENT, CREATION_DATE, EXECUTOR, STATE, CHECKLIST_VERSION, PROJECT_REVISION) VALUES (1, 'Example comment #1', '2018-04-30 10:18:09', 1, 1, 1, 1);
INSERT INTO REVISION_RESULT_ITEM (ID, VALUE, COMMENT, CREATION_DATE, STATE, REVISION_RESULT, CHECKLIST_ITEM) VALUES (1, 1, 'example comment item #1','2018-04-30 10:18:09', 1, 1, 1);
INSERT INTO REVISION_RESULT_ITEM (ID, VALUE, COMMENT, CREATION_DATE, STATE, REVISION_RESULT, CHECKLIST_ITEM) VALUES (2, 1, 'example comment item #2','2018-04-30 10:18:09', 1, 1, 2);
INSERT INTO REVISION_RESULT_ITEM (ID, VALUE, COMMENT, CREATION_DATE, STATE, REVISION_RESULT, CHECKLIST_ITEM) VALUES (3, 1, 'example comment item #3','2018-04-30 10:18:09', 0, 1, 3);

