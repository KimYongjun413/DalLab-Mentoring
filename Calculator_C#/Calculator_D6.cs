using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace Calculator
{
    public partial class Calculator_D6 : Form
    {
        public Calculator_D6()
        {        
            InitializeComponent();
            txtFormula.Select();
        }

        private void btn_Click(object sender, EventArgs e)
        {
            string sBtnText = (sender as Button).Text;

            if (sBtnText == "Clear") 
            {
                clearTextBox();             
            }
            else if (sBtnText == "Delete") 
            {
                if (txtFormula.Text != string.Empty)
                {
                    txtFormula.Text = txtFormula.Text.Substring(0, txtFormula.Text.Length - 1);
                    if (txtFormula.Text == string.Empty) clearTextBox();
                }                
            }
            else 
            {
                if (txtFormula.Text == string.Empty)
                {
                    if(sBtnText == "-") txtFormula.Text = "0-";
                    else if (sBtnText == "+") txtFormula.Text = "0+";
                    else if (sBtnText == "*") txtFormula.Text = "";
                    else if (sBtnText == "/") txtFormula.Text = "";
                    else txtFormula.Text += sBtnText;
                }                
                else
                {
                    txtFormula.Text += sBtnText;
                }
            }
        }   

        private void txtFormula_KeyPress(object sender, KeyPressEventArgs e)
        {
            if (!(char.IsDigit(e.KeyChar) || e.KeyChar == Convert.ToChar(Keys.Back)
                || "*" == Convert.ToString(e.KeyChar) || "/" == Convert.ToString(e.KeyChar)
                || "+" == Convert.ToString(e.KeyChar) || "-" == Convert.ToString(e.KeyChar)))
            {
                e.Handled = true;
            }

            if (e.KeyChar == Convert.ToChar(Keys.Back) && txtFormula.Text == string.Empty)
            {
                clearTextBox();
            }

            if (txtFormula.Text == string.Empty)
            {
                if ("-" == Convert.ToString(e.KeyChar))
                { txtFormula.Text = "0-"; e.Handled = true; }
                else if ("+" == Convert.ToString(e.KeyChar)) { txtFormula.Text = "0+"; e.Handled = true; }
                else if ("*" == Convert.ToString(e.KeyChar)) { txtFormula.Text = ""; e.Handled = true; }
                else if ("/" == Convert.ToString(e.KeyChar)) { txtFormula.Text = ""; e.Handled = true; }

                SendKeys.Send("{END}");                
            }
        }

        private void showResult()
        {            
            List<decimal> numlist = new List<decimal>();
            List<string> oprList = new List<string>();
            List<string> formulaList = new List<string>();


            #region Split을 사용하지 않고 문자13을 1,3이 아닌 13으로 인식시키는 방법을 찾지 못해, 그 대책으로 개발한 로직(개선해야 할 사항)
            string strFormula = txtFormula.Text;
            char[] cSeparator = { '+', '-', '*', '/' };
            string[] strNumber = txtFormula.Text.Split(cSeparator, StringSplitOptions.RemoveEmptyEntries);

            //연산자만 리스트에 따로 담는다.
            for (int c = 0; c < strFormula.Length; c++)
            {
                if (strFormula[c].ToString() == "*" || strFormula[c].ToString() == "/"
                    || strFormula[c].ToString() == "+" || strFormula[c].ToString() == "-")
                {
                    oprList.Add(strFormula[c].ToString());
                }
            }

            //입력한 수들을 리스트에 담는다.
            for (int n = 0; n < strNumber.Length; n++)
            {
                numlist.Add(Convert.ToDecimal(strNumber[n]));
            }                       
            
            #region 숫자와 연산자가 중위표기법 순으로 담겨있다.
            for (int i = 0; i < numlist.Count; i++)
            {
                formulaList.Add(numlist[i].ToString());
            }

            //연산자를 숫자 사이에 끼워 넣는다.
            if (formulaList.Count != 0 && oprList.Count < formulaList.Count)
            {
                for (int i = 0; i < oprList.Count; i++)
                {
                    formulaList.Insert(2 * i + 1, oprList[i].ToString());
                }
            }
            #endregion

            #endregion

            //괄호없는 다항식 4칙연산을 위해 '*','/'을 먼저 계산
            if (formulaList.Count > 2)
            {
                for (int i = 0; i < formulaList.Count - 1; i++)
                {
                    if (formulaList[i].ToString() == "*")
                    {
                        formulaList[i] = (Convert.ToDecimal(formulaList[i - 1]) * Convert.ToDecimal(formulaList[i + 1])).ToString();
                        formulaList.RemoveAt(i + 1);
                        formulaList.RemoveAt(i - 1);
                        i = 0;                        
                    }
                    else if (formulaList[i].ToString() == "/")
                    {
                        formulaList[i] = (Convert.ToDecimal(formulaList[i - 1]) / Convert.ToDecimal(formulaList[i + 1])).ToString();
                        formulaList.RemoveAt(i + 1);
                        formulaList.RemoveAt(i - 1);
                        i = 0;                        
                    }
                }                
            }

            txtResult.Text = calculation(formulaList);            

        }

        private void txtFormula_TextChanged(object sender, EventArgs e)
        {
            if (txtFormula.Text == string.Empty)
            {
                clearTextBox();
            }
            showResult();
        }

        private void clearTextBox()
        {
            txtResult.Text = string.Empty;
            txtFormula.Text = string.Empty;
        }

        /// <summary>
        /// '*','/'연산된 리스트를 받아 '+','-'연산 후 값을 리턴하는 함수
        /// </summary>
        /// <param name="pList"></param>
        /// <returns></returns>
        private string calculation(List<string> pList)
        {
            if (pList.Count == 0)
            {
                return "";
            }
            string strResult = string.Empty;

            for (int i = 0; i < pList.Count-1; i++)
            {
                if (pList[i].ToString() == "+")
                {
                    pList[i] = (Convert.ToDecimal(pList[i - 1]) + Convert.ToDecimal(pList[i + 1])).ToString();
                    pList.RemoveAt(i + 1);
                    pList.RemoveAt(i - 1);
                    i = 0;
                }
                else if (pList[i].ToString() == "-")
                {
                    pList[i] = (Convert.ToDecimal(pList[i - 1]) - Convert.ToDecimal(pList[i + 1])).ToString();
                    pList.RemoveAt(i + 1);
                    pList.RemoveAt(i - 1);
                    i = 0;
                }
            }

            strResult = pList[0].ToString();

            return strResult;

        }




    }
}
