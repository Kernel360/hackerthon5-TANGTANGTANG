-- 회원 테이블
CREATE TABLE member
(
    member_id INT PRIMARY KEY AUTO_INCREMENT,
    username  VARCHAR(255) NOT NULL,
    password  VARCHAR(255) NOT NULL
);

-- 방 테이블
CREATE TABLE room
(
    room_id INT PRIMARY KEY AUTO_INCREMENT,
    name    VARCHAR(255) NOT NULL
);

-- 좌석 테이블
CREATE TABLE seat
(
    seat_id INT PRIMARY KEY AUTO_INCREMENT,
    name    VARCHAR(255) NOT NULL,
    room_id INT          NOT NULL,
    FOREIGN KEY (room_id) REFERENCES room (room_id)
);

-- 좌석 시간 테이블
CREATE TABLE seat_time
(
    time_id  INT PRIMARY KEY AUTO_INCREMENT,
    seat_id  INT      NOT NULL,
    start_dt DATETIME NOT NULL, -- 예약 시작 일시 ex) 2024-10-01 09:30
    end_dt   DATETIME NOT NULL, -- 예약 종료 일시 ex) 2024-10-01 10:00
    FOREIGN KEY (seat_id) REFERENCES seat (seat_id)
);

-- 좌석 예약 테이블
CREATE TABLE seat_resv
(
    resv_id     INT PRIMARY KEY AUTO_INCREMENT,
    time_id     INT      NOT NULL,
    member_id   INT      NOT NULL,
    status      TINYINT  NOT NULL,
    reserved_at DATETIME NOT NULL,
    canceled_at DATETIME DEFAULT NULL,
    FOREIGN KEY (time_id) REFERENCES seat_time (time_id),
    FOREIGN KEY (member_id) REFERENCES member (member_id)
);

-- 데이터 삽입
INSERT INTO member (username, password)
VALUES ('user1', '{noop}1234');

INSERT INTO room (name)
VALUES ('회의실 A');

INSERT INTO seat (name, room_id)
VALUES ('좌석 1', 1);
INSERT INTO seat (name, room_id)
VALUES ('좌석 2', 1);
INSERT INTO seat (name, room_id)
VALUES ('좌석 3', 1);
INSERT INTO seat (name, room_id)
VALUES ('좌석 4', 1);
INSERT INTO seat (name, room_id)
VALUES ('좌석 5', 1);

INSERT INTO seat_time (seat_id, start_dt, end_dt)
VALUES (1, '2024-10-01 09:00', '2024-10-01 10:00');
INSERT INTO seat_time (seat_id, start_dt, end_dt)
VALUES (2, '2024-10-01 10:00', '2024-10-01 11:00');
INSERT INTO seat_time (seat_id, start_dt, end_dt)
VALUES (3, '2024-10-01 11:00', '2024-10-01 12:00');
INSERT INTO seat_time (seat_id, start_dt, end_dt)
VALUES (4, '2024-10-01 09:00', '2024-10-01 10:00');
INSERT INTO seat_time (seat_id, start_dt, end_dt)
VALUES (5, '2024-10-01 10:00', '2024-10-01 11:00');

INSERT INTO seat_resv (time_id, member_id, status, reserved_at, canceled_at)
VALUES (1, 1, 1, '2024-09-30 09:00', NULL);
INSERT INTO seat_resv (time_id, member_id, status, reserved_at, canceled_at)
VALUES (2, 1, 1, '2024-09-30 09:30', NULL);
INSERT INTO seat_resv (time_id, member_id, status, reserved_at, canceled_at)
VALUES (3, 1, 1, '2024-09-30 10:00', NULL);
INSERT INTO seat_resv (time_id, member_id, status, reserved_at, canceled_at)
VALUES (4, 1, 1, '2024-09-30 10:30', NULL);
INSERT INTO seat_resv (time_id, member_id, status, reserved_at, canceled_at)
VALUES (5, 1, 2, '2024-09-30 11:00', '2024-09-30 11:30');
