SET FOREIGN_KEY_CHECKS = 0;

truncate table blog_post;

truncate table author;

truncate table comment;

truncate table author_posts;

INSERT INTO blog_post(post_id, title, content, date_created)
    VALUES(41, 'Title post 1', 'Post Content 1', '2021-06-03T11:33:56'),
        (42, 'Title post 2', 'Post Content 2', '2021-06-03T11:43:56'),
        (43, 'Title post 3', 'Post Content 3', '2021-06-03T11:44:56'),
        (44, 'Title post 4', 'Post Content 4', '2021-06-03T11:53:56'),
        (45, 'Title post 5', 'Post Content 5', '2021-06-03T11:57:56');

SET FOREIGN_KEY_CHECKS = 1;