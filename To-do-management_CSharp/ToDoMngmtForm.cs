using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

using System.IO;

namespace To_do_management_CSharp
{
    public partial class ToDoMngmtForm : Form
    {

        DataTable exerciseInfoTable = new DataTable();
        TimeSpan totTime = new TimeSpan();

        public ToDoMngmtForm()
        {
            InitializeComponent();
        }

        private void btnUpload_Click(object sender, EventArgs e)
        {

            string fileName = string.Empty;
            DataTable rawTable = new DataTable();
            
            openFileDialog.Filter = "CSV Files (*.csv)|*.csv";

            if (openFileDialog.ShowDialog() == System.Windows.Forms.DialogResult.OK)
            {
                fileName = openFileDialog.FileName;
                rawTable = GetDataTableFromCsv(fileName, false);
            }

            if (fileName.Equals(string.Empty)) return;
            exerciseInfoTable = rawTable.DefaultView.ToTable(false, new string[] { "exercise_type", "start_time", "end_time" });
            dataGrid.DataSource = exerciseInfoTable;

        }


        private void btnSave_Click(object sender, EventArgs e)
        {
            string filePath = txtPath.Text;

            resultGrid.DataSource = GetResultTable(exerciseInfoTable);

            if (totTime.TotalMinutes > 50)
            {
                MessageBox.Show("유튜브를 해도 좋습니다!");
            }
            else
            {
                MessageBox.Show("운동도 덜 하고 유튜브를 하려 하다니!! 더 운동하고 오세요!!");
                File.AppendAllText(@"C:\\Windows\\System32\\drivers\\etc\\hosts", "127.0.0.1    www.youtube.com", Encoding.UTF8);
            }

            

        }

        private DataTable GetDataTableFromCsv(string path, bool isFirstRowHeader)
        {
            DataTable dt = new DataTable();
            using (StreamReader sr = new StreamReader(path))
            {
                sr.ReadLine();
                string[] headers = sr.ReadLine().Split(',');
                foreach (string header in headers)
                {
                    dt.Columns.Add(header);
                }
                while (!sr.EndOfStream)
                {
                    string[] rows = sr.ReadLine().Split(',');
                    DataRow dr = dt.NewRow();
                    for (int i = 0; i < headers.Length; i++)
                    {
                        dr[i] = rows[i];
                    }
                    dt.Rows.Add(dr);
                }

            }

            return dt;

        }

        private DataTable GetResultTable(DataTable pExcerciseInfoTable)
        {
            DataTable resultTable = new DataTable();

            DataTable tmpTable = new DataTable();
            tmpTable.Merge(pExcerciseInfoTable, false, MissingSchemaAction.Add);

            tmpTable.Columns.Add("exercise_time");            
            string strExcsType = string.Empty;            

            for (int iRow = 0; iRow < tmpTable.Rows.Count; iRow++)
            {
                strExcsType = tmpTable.Rows[iRow]["exercise_type"].ToString();

                if (strExcsType.Equals("10004") || strExcsType.Equals("10005") ||
                    strExcsType.Equals("10012") || strExcsType.Equals("10025"))
                {

                    if (!isToday(tmpTable.Rows[iRow]["end_time"].ToString()))
                    {
                        tmpTable.Rows.RemoveAt(iRow);
                        iRow--;
                    }
                    else
                    {
                        if (strExcsType.Equals("10004")) tmpTable.Rows[iRow]["exercise_type"] = "Push-ups (Press-ups)";
                        else if (strExcsType.Equals("10005")) tmpTable.Rows[iRow]["exercise_type"] = "Pull-ups (Chin-up)";
                        else if (strExcsType.Equals("10012")) tmpTable.Rows[iRow]["exercise_type"] = "Squats";
                        else if (strExcsType.Equals("10025")) tmpTable.Rows[iRow]["exercise_type"] = "Plank";


                        tmpTable.Rows[iRow]["exercise_time"] = Convert.ToDateTime(tmpTable.Rows[iRow]["end_time"]) - Convert.ToDateTime(tmpTable.Rows[iRow]["start_time"]);
                        totTime = totTime.Add(TimeSpan.Parse(tmpTable.Rows[iRow]["exercise_time"].ToString()));
                    }
                }
                else
                {
                    tmpTable.Rows.RemoveAt(iRow);
                    iRow--;
                }
            }

            tmpTable.Rows.Add();
            tmpTable.Rows[tmpTable.Rows.Count - 1]["exercise_type"] = "총 운동시간";
            tmpTable.Rows[tmpTable.Rows.Count - 1]["exercise_time"] = totTime.Hours + "시간 " + totTime.Minutes + "분 " + totTime.Seconds + "초";

            resultTable = tmpTable;

            txtTotalTime.Text = totTime.Hours + "시간 " + totTime.Minutes + "분 " + totTime.Seconds + "초";

            return resultTable;
        }

        private bool isToday(string pDate)
        {
            bool result = false;

            //DateTime now = DateTime.Today;
            DateTime now = new DateTime(2019, 4, 17);

            DateTime date = Convert.ToDateTime(pDate);

            if(now.ToString("d").Equals(date.ToString("d")))
            {
                result = true;
            }

            return result;
        }

        
    }
}
