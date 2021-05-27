SET FOREIGN_KEY_CHECKS = 0;

truncate table blog_post;

truncate table author;

truncate table comment;

truncate table author_posts;

INSERT INTO blog_post(post_id, title, content)
    VALUES(41, 'Title post 1', 'Post Content 1'),
        (42, 'Title post 2', 'Post Content 2'),
        (43, 'Title post 3', 'Post Content 3'),
        (44, 'Title post 4', 'Post Content 4'),
        (45, 'Title post 5', 'Post Content 5');

SET FOREIGN_KEY_CHECKS = 1;