package com.example.experiment17;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "courses.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_COURSES = "COURSE";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_DURATION = "Duration";
    private static final String COLUMN_DESCRIPTION = "Description";

    // SQL query to create the table
    private static final String CREATE_COURSE_TABLE = "CREATE TABLE " + TABLE_COURSES + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT, "
            + COLUMN_DURATION + " TEXT, "
            + COLUMN_DESCRIPTION + " TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_COURSE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
        onCreate(db);
    }

    // Add a new course
    public void addCourse(String name, String duration, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DURATION, duration);
        values.put(COLUMN_DESCRIPTION, description);
        db.insert(TABLE_COURSES, null, values);
        db.close();
    }

    // Get all courses
    public List<Course> getAllCourses() {
        List<Course> courseList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_COURSES, null, null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                Course course = new Course();
                course.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                course.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                course.setDuration(cursor.getString(cursor.getColumnIndex(COLUMN_DURATION)));
                course.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
                courseList.add(course);
            }
            cursor.close();
        }
        db.close();
        return courseList;
    }

    // Update a course
    public void updateCourse(int id, String name, String duration, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DURATION, duration);
        values.put(COLUMN_DESCRIPTION, description);
        db.update(TABLE_COURSES, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    // Delete a course
    public void deleteCourse(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_COURSES, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}
