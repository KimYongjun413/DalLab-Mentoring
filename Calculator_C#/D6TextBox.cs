using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using System.Windows.Forms;
using System.ComponentModel;

namespace Calculator
{
    public partial class D6TextBox : TextBox
    {
        [DefaultValue(false)]
        [Browsable(true)]
        public override bool AutoSize
        {
            get
            {
                return base.AutoSize;
            }
            set
            {
                base.AutoSize = value;
            }
        }

        public D6TextBox()
        {
            this.AutoSize = false;
        }
    }
}
