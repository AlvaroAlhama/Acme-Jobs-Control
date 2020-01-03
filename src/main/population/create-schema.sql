
    create table `administrator` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `aguilar_bulletin` (
       `id` integer not null,
        `version` integer not null,
        `company` varchar(255),
        `moment` datetime(6),
        `requirement` varchar(255),
        `salary` integer not null,
        `vacancy` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `announcement` (
       `id` integer not null,
        `version` integer not null,
        `moment` datetime(6),
        `more_info` varchar(255),
        `text` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `anonymous` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `application` (
       `id` integer not null,
        `version` integer not null,
        `moment` datetime(6),
        `qualifications` varchar(255),
        `reference` varchar(255),
        `resolution_justification` varchar(255),
        `resolution_moment` datetime(6),
        `skills` varchar(255),
        `statement` varchar(255),
        `status` integer,
        `job_id` integer not null,
        `worker_id` integer not null,
        `xxxxapplication_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `audit_records` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `creation_moment` datetime(6),
        `status` integer,
        `title` varchar(255),
        `auditor_id` integer not null,
        `job_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `auditor` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `firm` varchar(255),
        `responsibility_statement` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `authenticated` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `challenge` (
       `id` integer not null,
        `version` integer not null,
        `bronze_goal` varchar(255),
        `bronze_reward_amount` double precision,
        `bronze_reward_currency` varchar(255),
        `deadline` datetime(6),
        `description` varchar(255),
        `gold_goal` varchar(255),
        `gold_reward_amount` double precision,
        `gold_reward_currency` varchar(255),
        `silver_goal` varchar(255),
        `silver_reward_amount` double precision,
        `silver_reward_currency` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `cobo_bulletin` (
       `id` integer not null,
        `version` integer not null,
        `author` varchar(255),
        `creation_date` datetime(6),
        `description` varchar(255),
        `location` varchar(255),
        `salary` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `commercial` (
       `id` integer not null,
        `version` integer not null,
        `picture` varchar(255),
        `slogan` varchar(255),
        `target` varchar(255),
        `sponsor_id` integer,
        `card_holder` varchar(255),
        `credit_card_number` varchar(255),
        `cvv` varchar(255),
        `expiration_date` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `company_records` (
       `id` integer not null,
        `version` integer not null,
        `activity_desc` varchar(255),
        `ceo_name` varchar(255),
        `company_name` varchar(255),
        `contact_email` varchar(255),
        `contact_phone` varchar(255),
        `incorporated` bit,
        `rating` integer,
        `web_site` varchar(255),
        `work_sector` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `consumer` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `company` varchar(255),
        `sector` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `credit_card` (
       `id` integer not null,
        `version` integer not null,
        `card_holder` varchar(255),
        `credit_card_number` varchar(255),
        `cvv` varchar(255),
        `expiration_date` varchar(255),
        `sponsor_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `customisation_parameters` (
       `id` integer not null,
        `version` integer not null,
        `identifier` integer,
        `spam_list` varchar(255),
        `spam_threshold` float,
        primary key (`id`)
    ) engine=InnoDB;

    create table `descriptor` (
       `id` integer not null,
        `version` integer not null,
        `description` varchar(255),
        `job_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `doblado_bulletin` (
       `id` integer not null,
        `version` integer not null,
        `degree` varchar(255),
        `location` varchar(255),
        `moment` datetime(6),
        `name` varchar(255),
        `text` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `duty` (
       `id` integer not null,
        `version` integer not null,
        `description_duty` varchar(255),
        `percentage` integer,
        `title_duty` varchar(255),
        `descriptor_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `employer` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `company` varchar(255),
        `sector` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `investor_record` (
       `id` integer not null,
        `version` integer not null,
        `name` varchar(255),
        `sector` varchar(255),
        `stars` integer,
        `statement` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `involved` (
       `id` integer not null,
        `version` integer not null,
        `message_thread_id` integer not null,
        `user_account_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `job` (
       `id` integer not null,
        `version` integer not null,
        `deadline` datetime(6),
        `more_info` varchar(255),
        `reference` varchar(255),
        `salary_amount` double precision,
        `salary_currency` varchar(255),
        `status` integer,
        `title` varchar(255),
        `employer_id` integer not null,
        `xxxx_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `message` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `moment` datetime(6),
        `tags` varchar(255),
        `title` varchar(255),
        `creator_id` integer not null,
        `message_thread_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `message_thread` (
       `id` integer not null,
        `version` integer not null,
        `moment` datetime(6),
        `title` varchar(255),
        `creator_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `munoz_bulletin` (
       `id` integer not null,
        `version` integer not null,
        `group_name` varchar(255),
        `manager_name` varchar(255),
        `moment` datetime(6),
        `size` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `non_commercial` (
       `id` integer not null,
        `version` integer not null,
        `picture` varchar(255),
        `slogan` varchar(255),
        `target` varchar(255),
        `sponsor_id` integer,
        `jingle` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `offer` (
       `id` integer not null,
        `version` integer not null,
        `creation_moment` datetime(6),
        `deadline` datetime(6),
        `description` varchar(255),
        `max_reward_amount` double precision,
        `max_reward_currency` varchar(255),
        `min_reward_amount` double precision,
        `min_reward_currency` varchar(255),
        `ticker` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `provider` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `company` varchar(255),
        `sector` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `quintela_bulletin` (
       `id` integer not null,
        `version` integer not null,
        `author` varchar(255),
        `created_at` datetime(6),
        `deadline_date` datetime(6),
        `priority` integer,
        `text` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `reina_bulletin` (
       `id` integer not null,
        `version` integer not null,
        `age` integer,
        `author` varchar(255),
        `create_date` datetime(6),
        `text` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `request` (
       `id` integer not null,
        `version` integer not null,
        `created_at` datetime(6),
        `deadline` datetime(6),
        `description` varchar(255),
        `reward_amount` double precision,
        `reward_currency` varchar(255),
        `ticker` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `request_auditor` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `firm` varchar(255),
        `responsibility_statement` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `sponsor` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `organisation_name` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `user_account` (
       `id` integer not null,
        `version` integer not null,
        `enabled` bit not null,
        `identity_email` varchar(255),
        `identity_name` varchar(255),
        `identity_surname` varchar(255),
        `password` varchar(255),
        `username` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `worker` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `qualifications` varchar(255),
        `skills` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `xxxx` (
       `id` integer not null,
        `version` integer not null,
        `link_info` varchar(255),
        `piece_of_text` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `xxxx_application` (
       `id` integer not null,
        `version` integer not null,
        `answer` varchar(255),
        `password` varchar(255),
        `xxxx` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `hibernate_sequence` (
       `next_val` bigint
    ) engine=InnoDB;

    insert into `hibernate_sequence` values ( 1 );
create index IDXnhikaa2dj3la6o2o7e9vo01y0 on `announcement` (`moment`);
create index IDX2q2747fhp099wkn3j2yt05fhs on `application` (`status`);
create index IDXdwumdwpjcwdk1mef9ua69yc2p on `application` (`reference`);
create index IDXg54pxa1gngqheaipukeg8jypk on `application` (`moment`);
create index IDXt38vobsvxk1wmkq6ssk97rul1 on `application` (`resolution_moment`);

    alter table `application` 
       add constraint UK_ct7r18vvxl5g4c4k7aefpa4do unique (`reference`);
create index IDXlj2d4j7euwcsk20ok2xgjxwl0 on `audit_records` (`status`);
create index IDXnr284tes3x8hnd3h716tmb3fr on `challenge` (`deadline`);
create index IDX6nd7baccjosrbgxx13s15d859 on `company_records` (`rating`);
create index IDXmlt8tvsyjfedmoqiivkl6s03c on `company_records` (`work_sector`);

    alter table `credit_card` 
       add constraint UK_4cr95y27s8ti6otoyflmma6oy unique (`sponsor_id`);
create index IDXjaub8uhu1ab9se7oh9atwuktl on `customisation_parameters` (`identifier`);

    alter table `descriptor` 
       add constraint UK_4iw18njo4d0q8gvnhe04vmctw unique (`job_id`);
create index IDXk2t3uthe649ao1jllcuks0gv4 on `investor_record` (`stars`);
create index IDX29vxwf0tu7wf2iwmss2d07hql on `investor_record` (`sector`);
create index IDXal59yunywnkwi09ps7jxpr18c on `job` (`deadline`, `status`);
create index IDX28ur9xm72oo1df9g14xhnh8h3 on `job` (`status`);
create index IDX8ix743uifflnrs9bupbn6y0h4 on `job` (`reference`);

    alter table `job` 
       add constraint UK_7jmfdvs0b0jx7i33qxgv22h7b unique (`reference`);
create index IDXjporswtrt7iirg3sca9fipjj4 on `message` (`title`);
create index IDX3pvpt477dc7b3lairb4qjna7m on `message_thread` (`title`);
create index IDXq2o9psuqfuqmq59f0sq57x9uf on `offer` (`deadline`);
create index IDXcp4664f36sgqsd0ihmirt0w0 on `offer` (`ticker`);

    alter table `offer` 
       add constraint UK_iex7e8fs0fh89yxpcnm1orjkm unique (`ticker`);
create index IDXlrvsw21ylkdqa1shrkwg1yssx on `request` (`deadline`);
create index IDXh9syauj4iixf18uts83saik5d on `request` (`ticker`);

    alter table `request` 
       add constraint UK_9mxq3powq8tqctclj0fbi2nih unique (`ticker`);

    alter table `user_account` 
       add constraint UK_castjbvpeeus0r8lbpehiu0e4 unique (`username`);

    alter table `administrator` 
       add constraint FK_2a5vcjo3stlfcwadosjfq49l1 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `anonymous` 
       add constraint FK_6lnbc6fo3om54vugoh8icg78m 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `application` 
       add constraint `FKoa6p4s2oyy7tf80xwc4r04vh6` 
       foreign key (`job_id`) 
       references `job` (`id`);

    alter table `application` 
       add constraint `FKmbjdoxi3o93agxosoate4sxbt` 
       foreign key (`worker_id`) 
       references `worker` (`id`);

    alter table `application` 
       add constraint `FKge315cg5by8qehd79a5ubf83j` 
       foreign key (`xxxxapplication_id`) 
       references `xxxx_application` (`id`);

    alter table `audit_records` 
       add constraint `FKl6b73crbwej8f95bvp1npqm8p` 
       foreign key (`auditor_id`) 
       references `auditor` (`id`);

    alter table `audit_records` 
       add constraint `FK25q3rsnsluma5vbn99874y30o` 
       foreign key (`job_id`) 
       references `job` (`id`);

    alter table `auditor` 
       add constraint FK_clqcq9lyspxdxcp6o4f3vkelj 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `authenticated` 
       add constraint FK_h52w0f3wjoi68b63wv9vwon57 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `commercial` 
       add constraint FK_tk5yvuytfoa0dgtibahrxwwkd 
       foreign key (`sponsor_id`) 
       references `sponsor` (`id`);

    alter table `consumer` 
       add constraint FK_6cyha9f1wpj0dpbxrrjddrqed 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `credit_card` 
       add constraint `FK31l5hvh7p1nx1aw6v649gw3rc` 
       foreign key (`sponsor_id`) 
       references `sponsor` (`id`);

    alter table `descriptor` 
       add constraint `FKgfulfilmwi4hhaquiu7fr5g0g` 
       foreign key (`job_id`) 
       references `job` (`id`);

    alter table `duty` 
       add constraint `FK3cc3garl37bl7gswreqwr7pj4` 
       foreign key (`descriptor_id`) 
       references `descriptor` (`id`);

    alter table `employer` 
       add constraint FK_na4dfobmeuxkwf6p75abmb2tr 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `involved` 
       add constraint `FKhkjx1r325hanpggn2t7dlad23` 
       foreign key (`message_thread_id`) 
       references `message_thread` (`id`);

    alter table `involved` 
       add constraint `FK6ki4eammiocj9p3amqm7v4ej9` 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `job` 
       add constraint `FK3rxjf8uh6fh2u990pe8i2at0e` 
       foreign key (`employer_id`) 
       references `employer` (`id`);

    alter table `job` 
       add constraint `FKag95kov9qvw4wnpeuxg448lme` 
       foreign key (`xxxx_id`) 
       references `xxxx` (`id`);

    alter table `message` 
       add constraint `FKn4cg0xtxxw96f3n2ba6k1epnq` 
       foreign key (`creator_id`) 
       references `user_account` (`id`);

    alter table `message` 
       add constraint `FKn5adlx3oqjna7aupm8gwg3fuj` 
       foreign key (`message_thread_id`) 
       references `message_thread` (`id`);

    alter table `message_thread` 
       add constraint `FKr35u0eaupbx6b2w22e33u8s5u` 
       foreign key (`creator_id`) 
       references `user_account` (`id`);

    alter table `non_commercial` 
       add constraint FK_1px28k1t0j3coqn549p1ru8op 
       foreign key (`sponsor_id`) 
       references `sponsor` (`id`);

    alter table `provider` 
       add constraint FK_b1gwnjqm6ggy9yuiqm0o4rlmd 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `request_auditor` 
       add constraint FK_ie2ocrruj5nai12m6h4a0fmtw 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `sponsor` 
       add constraint FK_20xk0ev32hlg96kqynl6laie2 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `worker` 
       add constraint FK_l5q1f33vs2drypmbdhpdgwfv3 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);
