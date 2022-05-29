package com.example.opensourcefinal

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.navigation.findNavController
import java.util.*
import java.util.concurrent.TimeUnit


//메모 기능 추가


class dateFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//
//
//
//        //여기 데이트 Fragment에서 D-Day 추가할꺼야. D-Day 카운터 기능 추가해서 화면에 보여주기.
//        val startBtn = view!!.findViewById<Button>(R.id.startBtn)
//        val endBtn = view!!.findViewById<Button>(R.id.endBtn)
//
//        var startDate = ""   //계속 바뀌는 데이터여야 하니까 var
//        var endDate = ""   //계속 바뀌는 데이터여야 하니까 var
//
//        val calendar_start = Calendar.getInstance()
//        val calendar_end = Calendar.getInstance()
//
//
//        startBtn.setOnClickListener {
//            //시작일 버튼을 클릭 시 날짜 다이얼로그 띄우자
//            val today = GregorianCalendar()
//            val year : Int = today.get(Calendar.YEAR)
//            val month : Int = today.get(Calendar.MONTH)
//            val day : Int = today.get(Calendar.DATE)
//            //연 월 일 뽑아온 뒤
//            calendar_end.set(year,month+1,day)
//
//
////            type mismatch 에러 계속 뜨는 이유를 모름
//            val dlg = DatePickerDialog(view!!.context , object : DatePickerDialog.OnDateSetListener{
//                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
//                    //날짜 선택한 다음 이벤트.
//                    startDate = "${year} + ${month + 1} + ${dayOfMonth}"   //시작일
//                    Log.d("day", startDate)
////
////                    calendar_start.set(year,month+1, dayOfMonth)
////                    val calDate = TimeUnit.MILLISECONDS.toDays(calendar_end.timeInMillis - calendar_start.timeInMillis)
//
//                    val textArea = view!!.findViewById<TextView>(R.id.countDate)
////                    if (textArea != null) {
////                        textArea.setText("${year} + ${month + 1} + ${dayOfMonth}".toString())
////                    }   //카운터 설정.
//                    textArea.text = "${year} + ${month + 1} + ${dayOfMonth}"
////                    textArea.setText("${year} + ${month + 1} + ${dayOfMonth}")
//                    Log.d("day", textArea?.text.toString())
//                }
//
//            }, year, month, day)
//            dlg.show()
//        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_date, container, false)



        //여기 데이트 Fragment에서 D-Day 추가할꺼야. D-Day 카운터 기능 추가해서 화면에 보여주기.
        val startBtn = view.findViewById<Button>(R.id.startBtn)
        val endBtn = view.findViewById<Button>(R.id.endBtn)

        var startDate = ""   //계속 바뀌는 데이터여야 하니까 var
        var endDate = ""   //계속 바뀌는 데이터여야 하니까 var

        val calendar_start = Calendar.getInstance()
        val calendar_end = Calendar.getInstance()

        val textarea = view.findViewById<TextView>(R.id.countDate)  // 밖에다 선언해줬어야함.



        startBtn.setOnClickListener {
            //시작일 버튼을 클릭 시 날짜 다이얼로그 띄우자
            val today = GregorianCalendar()
            val year : Int = today.get(Calendar.YEAR)
            val month : Int = today.get(Calendar.MONTH)
            val day : Int = today.get(Calendar.DATE)
            //연 월 일 뽑아온 뒤
//            calendar_end.set(year,month+1,day)


//            type mismatch 에러 계속 뜨는 이유를 모름
            val dlg = DatePickerDialog(view.context , object : DatePickerDialog.OnDateSetListener{
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    //날짜 선택한 다음 이벤트.
                    startDate = "${year} - ${month + 1} - ${dayOfMonth}"   //시작일
                    Log.d("day", startDate)
//
                    calendar_start.set(year,month+1, dayOfMonth)
                    val calDate = TimeUnit.MILLISECONDS.toDays(calendar_end.timeInMillis - calendar_start.timeInMillis)   //사귄 날짜에 대해서 millisecond로 변환

                    textarea.text = startDate
                    Log.d("textarea", textarea.text.toString())

//                    var textArea = view?.findViewById<TextView>(R.id.countDate)
//                    Log.d("textArea", textArea.toString())
////                    if (textArea != null) {
////                        textArea.setText("${year} + ${month + 1} + ${dayOfMonth}".toString())
////                    }   //카운터 설정.
////                    textArea!!.text = startDate
//                    textArea?.setText(startDate)
////                    textArea.invalidate()
////                    textArea.setText("${year} + ${month + 1} + ${dayOfMonth}")
//                    Log.d("lalala", textArea?.text.toString())
                }

            }, year, month, day)
            dlg.show()
        }

        endBtn.setOnClickListener {
            //종료일  버튼을 클릭 시
            val today = GregorianCalendar()
            val year = today.get(Calendar.YEAR)
            val month = today.get(Calendar.MONTH)
            val day = today.get(Calendar.DATE)
            //연 월 일 뽑아온 뒤



            //type mismatch 에러 계속 뜨는 이유를 모름
            val dlg = DatePickerDialog(view.context , object : DatePickerDialog.OnDateSetListener{
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    endDate = "${year} + ${month + +1} + ${dayOfMonth + 1}"   //시작일
                    Log.d("day2", endDate)

                    calendar_end.set(year,month+1, dayOfMonth + 1)

                    val calDate = TimeUnit.MILLISECONDS.toDays(calendar_end.timeInMillis - calendar_start.timeInMillis)   //오늘에 대한 날짜를 millsecond로 변환

//                    var textArea = view!!.findViewById<TextView>(R.id.countDate)
//                    textArea.setText(calDate.toString())   //카운터 설정.
                    textarea.text = "D + " + calDate.toString()
                }

            }, year, month, day)
            dlg.show()
        }



        return view.rootView
    }


}