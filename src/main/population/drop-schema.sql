
    alter table `administrator` 
       drop 
       foreign key FK_2a5vcjo3stlfcwadosjfq49l1;

    alter table `anonymous` 
       drop 
       foreign key FK_6lnbc6fo3om54vugoh8icg78m;

    alter table `application` 
       drop 
       foreign key `FKoa6p4s2oyy7tf80xwc4r04vh6`;

    alter table `application` 
       drop 
       foreign key `FKmbjdoxi3o93agxosoate4sxbt`;

    alter table `application` 
       drop 
       foreign key `FKtaxnyk5um71tfwvpd6yj0754`;

    alter table `audit_records` 
       drop 
       foreign key `FKl6b73crbwej8f95bvp1npqm8p`;

    alter table `audit_records` 
       drop 
       foreign key `FK25q3rsnsluma5vbn99874y30o`;

    alter table `auditor` 
       drop 
       foreign key FK_clqcq9lyspxdxcp6o4f3vkelj;

    alter table `authenticated` 
       drop 
       foreign key FK_h52w0f3wjoi68b63wv9vwon57;

    alter table `commercial` 
       drop 
       foreign key FK_tk5yvuytfoa0dgtibahrxwwkd;

    alter table `consumer` 
       drop 
       foreign key FK_6cyha9f1wpj0dpbxrrjddrqed;

    alter table `credit_card` 
       drop 
       foreign key `FK31l5hvh7p1nx1aw6v649gw3rc`;

    alter table `descriptor` 
       drop 
       foreign key `FKgfulfilmwi4hhaquiu7fr5g0g`;

    alter table `duty` 
       drop 
       foreign key `FK3cc3garl37bl7gswreqwr7pj4`;

    alter table `employer` 
       drop 
       foreign key FK_na4dfobmeuxkwf6p75abmb2tr;

    alter table `involved` 
       drop 
       foreign key `FKhkjx1r325hanpggn2t7dlad23`;

    alter table `involved` 
       drop 
       foreign key `FK6ki4eammiocj9p3amqm7v4ej9`;

    alter table `job` 
       drop 
       foreign key `FK3rxjf8uh6fh2u990pe8i2at0e`;

    alter table `job` 
       drop 
       foreign key `FKag95kov9qvw4wnpeuxg448lme`;

    alter table `message` 
       drop 
       foreign key `FKn4cg0xtxxw96f3n2ba6k1epnq`;

    alter table `message` 
       drop 
       foreign key `FKn5adlx3oqjna7aupm8gwg3fuj`;

    alter table `message_thread` 
       drop 
       foreign key `FKr35u0eaupbx6b2w22e33u8s5u`;

    alter table `non_commercial` 
       drop 
       foreign key FK_1px28k1t0j3coqn549p1ru8op;

    alter table `provider` 
       drop 
       foreign key FK_b1gwnjqm6ggy9yuiqm0o4rlmd;

    alter table `request_auditor` 
       drop 
       foreign key FK_ie2ocrruj5nai12m6h4a0fmtw;

    alter table `sponsor` 
       drop 
       foreign key FK_20xk0ev32hlg96kqynl6laie2;

    alter table `worker` 
       drop 
       foreign key FK_l5q1f33vs2drypmbdhpdgwfv3;

    drop table if exists `administrator`;

    drop table if exists `aguilar_bulletin`;

    drop table if exists `announcement`;

    drop table if exists `anonymous`;

    drop table if exists `application`;

    drop table if exists `audit_records`;

    drop table if exists `auditor`;

    drop table if exists `authenticated`;

    drop table if exists `challenge`;

    drop table if exists `cobo_bulletin`;

    drop table if exists `commercial`;

    drop table if exists `company_records`;

    drop table if exists `consumer`;

    drop table if exists `credit_card`;

    drop table if exists `customisation_parameters`;

    drop table if exists `descriptor`;

    drop table if exists `doblado_bulletin`;

    drop table if exists `duty`;

    drop table if exists `employer`;

    drop table if exists `investor_record`;

    drop table if exists `involved`;

    drop table if exists `job`;

    drop table if exists `message`;

    drop table if exists `message_thread`;

    drop table if exists `munoz_bulletin`;

    drop table if exists `non_commercial`;

    drop table if exists `offer`;

    drop table if exists `provider`;

    drop table if exists `quintela_bulletin`;

    drop table if exists `reina_bulletin`;

    drop table if exists `request`;

    drop table if exists `request_auditor`;

    drop table if exists `sponsor`;

    drop table if exists `user_account`;

    drop table if exists `worker`;

    drop table if exists `xxxx`;

    drop table if exists `xxxx_application`;

    drop table if exists `hibernate_sequence`;
