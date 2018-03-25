INSERT INTO category (id, name, color) VALUES (1, 'home', 'e83e8c');
INSERT INTO category (id, name, color) VALUES (2, 'work', '007bff');

INSERT INTO task (description, priority, status, category_id, created_date) VALUES ('Do it now', 1, 0, 1, now());
INSERT INTO task (description, priority, status, category_id, created_date) VALUES ('Do it again', 1, 0, 1, now());
INSERT INTO task (description, priority, status, category_id, created_date) VALUES ('Not now', 1, 0, 2, now());
INSERT INTO task (description, priority, status, category_id, created_date) VALUES ('Maybe later', 1, 0, 2, now());
INSERT INTO task (description, priority, status, category_id, created_date) VALUES ('Done Today', 1, 2, 2, now());
INSERT INTO task (description, priority, status, category_id, created_date) VALUES ('Done Yester', 1, 2, 2, now());
INSERT INTO task (description, priority, status, category_id, created_date) VALUES ('Done already', 1, 2, 1, now());