DELETE FROM user_tokens;
DELETE FROM users;

INSERT INTO users (user_id, first_name, second_name, birthdate, password, biography, gender, city) VALUES
  ('07e8882f-2396-40cf-8042-27149627e47b', 'Yuri', 'Gagarin', '1934-03-09', '$2a$10$/w6PKSMlhpA2vOiWqWetvuH9EZ8IMQdhFV0YwqzmuRBKiUAEaQpLC', 'spaceman', 'male', 'Smolensk'),
  ('2a4bc3bf-0b3d-42b7-a455-a263356fbd87','Alex', 'Vasilev', '1999-01-29', '$2a$10$oeNElAfWqkndofpQH5vBu.6ezB9UZXfI2AW6ZvQvibl5O3rkXQjj6', 'developer', 'male', 'Moscow');

