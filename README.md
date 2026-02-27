# Danh sách thành viên

| **Họ tên**           | **MSSV** |
| -------------------- | -------- |
| Nguyễn Phan Quốc Anh | 2310116  |
| Nguyễn Phan Việt Anh | 2310117  |

# Public URL của Web Service

https://student-management-re8n.onrender.com/

# Hướng dẫn cách chạy dự án

# Các câu hỏi lý thuyết

## Lab 1

### 1. Dữ liệu lớn: Hãy thử thêm ít nhất 10 sinh viên nữa.

```sql
INSERT INTO students (id, name, email, age) VALUES (3, 'Le Van C', 'levanc@example.com', 19);
INSERT INTO students (id, name, email, age) VALUES (4, 'Pham Thi D', 'phamthid@example.com', 22);
INSERT INTO students (id, name, email, age) VALUES (5, 'Hoang Van E', 'hoangvane@example.com', 17);
INSERT INTO students (id, name, email, age) VALUES (6, 'Vo Thi F', 'vothif@example.com', 15);
INSERT INTO students (id, name, email, age) VALUES (7, 'Dang Van G', 'dangvang@example.com', 23);
INSERT INTO students (id, name, email, age) VALUES (8, 'Bui Thi H', 'buithih@example.com', 19);
INSERT INTO students (id, name, email, age) VALUES (9, 'Do Van I', 'dovani@example.com', 22);
INSERT INTO students (id, name, email, age) VALUES (10, 'Nguyen Thi K', 'nguyenthik@example.com', 20);
INSERT INTO students (id, name, email, age) VALUES (11, 'Tran Van L', 'tranvanl@example.com', 21);
INSERT INTO students (id, name, email, age) VALUES (12, 'Le Thi M', 'lethim@example.com', 16);
```

### 2. Ràng buộc Khóa Chính (Primary Key):

- Cố tình Insert một sinh viên có `id` trùng với một người đã có sẵn.
  ```sql
  INSERT INTO students (id, name, email, age) VALUES (1, 'Pham Van X', 'phamvanx@example.com', 22);
  ```
- Quan sát thông báo lỗi: `UNIQUE constraint failed`. Tại sao Database lại chặn thao tác này?

  Database chặn thao tác INSERT vì giá trị `id` bị trùng với khóa chính đã tồn tại, vi phạm ràng buộc UNIQUE/PRIMARY KEY nhằm đảm bảo toàn vẹn dữ liệu và định danh duy nhất mỗi bản ghi trong bảng.

### 3. Toàn vẹn dữ liệu (Constraints):

- Thử Insert một sinh viên nhưng bỏ trống cột `name` (để NULL).
  ```sql
  INSERT INTO students (id, name, email, age) VALUES (13, NULL, 'noname@example.com', 20);
  ```
- Database có báo lỗi không? Từ đó suy nghĩ xem sự thiếu chặt chẽ này ảnh hưởng gì khi code Java đọc dữ liệu lên?

  Database không báo lỗi khi INSERT `NULL` vào cột `name` vì cột này không có ràng buộc `NOT NULL`.
  Sự thiếu chặt chẽ này có thể gây lỗi khi Java đọc dữ liệu, đặc biệt là `NullPointerException`, dữ liệu hiển thị sai hoặc vi phạm logic nghiệp vụ. Do đó, cần thiết kế ràng buộc chặt chẽ ngay từ Database và kết hợp kiểm tra ở tầng ứng dụng.

### 4. Cấu hình Hibernate:

- Tại sao mỗi lần tắt ứng dụng và chạy lại, dữ liệu cũ trong Database lại bị mất hết?

  Vì trong cấu hình Hibernate, `ddl-auto=create` có nghĩa là mỗi khi chạy lại app, Hibernate sẽ xóa dữ liệu cũ và tạo lại bảng mới.

# Screenshot các module trong Lab 4

<img width="1920" height="1140" alt="Screenshot 2026-02-27 124129" src="https://github.com/user-attachments/assets/e06e3f66-31ed-40c8-8320-766aa599ffd0" />
<img width="1920" height="1140" alt="Screenshot 2026-02-27 122727" src="https://github.com/user-attachments/assets/dbaa5224-e6a3-4002-b0a0-b7e82c740982" />
<img width="1920" height="1140" alt="Screenshot 2026-02-27 122757" src="https://github.com/user-attachments/assets/3b5eca3d-cee8-449f-a539-6f1ab81c635e" />
<img width="1920" height="1140" alt="Screenshot 2026-02-27 122840" src="https://github.com/user-attachments/assets/35be9013-e3ce-4c9b-b77f-018e818c5b07" />
