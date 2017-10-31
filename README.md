My solution is written in Java.

# Example

Suppose your input file contained only the following few lines. Note that the fields we are interested in are in **bold** below but will not be like that in the input file. There's also an extra new line between records below, but the input file won't have that.

> **C00629618**|N|TER|P|201701230300133512|15C|IND|PEREZ, JOHN A|LOS ANGELES|CA|**90017**|PRINCIPAL|DOUBLE NICKEL ADVISORS|**01032017**|**40**|**H6CA34245**|SA01251735122|1141239|||2012520171368850783

> **C00177436**|N|M2|P|201702039042410894|15|IND|DEEHAN, WILLIAM N|ALPHARETTA|GA|**300047357**|UNUM|SVP, SALES, CL|**01312017**|**384**||PR2283873845050|1147350||P/R DEDUCTION ($192.00 BI-WEEKLY)|4020820171370029337

> **C00384818**|N|M2|P|201702039042412112|15|IND|ABBOTT, JOSEPH|WOONSOCKET|RI|**028956146**|CVS HEALTH|VP, RETAIL PHARMACY OPS|**01122017**|**250**||2017020211435-887|1147467|||4020820171370030285

> **C00177436**|N|M2|P|201702039042410893|15|IND|SABOURIN, JAMES|LOOKOUT MOUNTAIN|GA|**307502818**|UNUM|SVP, CORPORATE COMMUNICATIONS|**01312017**|**230**||PR1890575345050|1147350||P/R DEDUCTION ($115.00 BI-WEEKLY)|4020820171370029335

> **C00177436**|N|M2|P|201702039042410895|15|IND|JEROME, CHRISTOPHER|FALMOUTH|ME|**041051896**|UNUM|EVP, GLOBAL SERVICES|**01312017**|**384**||PR2283905245050|1147350||P/R DEDUCTION ($192.00 BI-WEEKLY)|4020820171370029342

> **C00384818**|N|M2|P|201702039042412112|15|IND|BAKER, SCOTT|WOONSOCKET|RI|**028956146**|CVS HEALTH|EVP, HEAD OF RETAIL OPERATIONS|**01122017**|**333**||2017020211435-910|1147467|||4020820171370030287

> **C00177436**|N|M2|P|201702039042410894|15|IND|FOLEY, JOSEPH|FALMOUTH|ME|**041051935**|UNUM|SVP, CORP MKTG & PUBLIC RELAT.|**01312017**|**384**||PR2283904845050|1147350||P/R DEDUCTION ($192.00 BI-WEEKLY)|4020820171370029339

If we were to pick the relevant fields from each line, here is what we would record for each line.

    1.
    CMTE_ID: C00629618
    ZIP_CODE: 90017
    TRANSACTION_DT: 01032017
    TRANSACTION_AMT: 40
    OTHER_ID: H6CA34245

    2.
    CMTE_ID: C00177436
    ZIP_CODE: 30004
    TRANSACTION_DT: 01312017
    TRANSACTION_AMT: 384
    OTHER_ID: empty

    3. 
    CMTE_ID: C00384818
    ZIP_CODE: 02895
    TRANSACTION_DT: 01122017
    TRANSACTION_AMT: 250
    OTHER_ID: empty

    4.
    CMTE_ID: C00177436
    ZIP_CODE: 30750
    TRANSACTION_DT: 01312017
    TRANSACTION_AMT: 230
    OTHER_ID: empty

    5.
    CMTE_ID: C00177436
    ZIP_CODE: 04105
    TRANSACTION_DT: 01312017
    TRANSACTION_AMT: 384
    OTHER_ID: empty

    6.
    CMTE_ID: C00384818
    ZIP_CODE: 02895
    TRANSACTION_DT: 01122017
    TRANSACTION_AMT: 333
    OTHER_ID: empty

    7.
    CMTE_ID: C00177436
    ZIP_CODE: 04105
    TRANSACTION_DT: 01312017
    TRANSACTION_AMT: 384
    OTHER_ID: empty



We would ignore the first record because the `OTHER_ID` field contains data and is not empty. Moving to the next record, we would write out the first line of `medianvals_by_zip.txt` to be:

`C00177436|30004|384|1|384`

Note that because we have only seen one record streaming in for that recipient and zip code, the running median amount of contribution and total amount of contribution is `384`. 

Looking through the other lines, note that there are only two recipients for all of the records we're interested in our input file (minus the first line that was ignored due to non-null value of `OTHER_ID`). 

Also note that there are two records with the recipient `C00177436` and zip code of `04105` totaling $768 in contributions while the recipient `C00384818` and zip code `02895` has two contributions totaling $583 (250 + 333) and a median of $292 (583/2 = 291.5 or 292 when rounded up) 

Processing all of the input lines, the entire contents of `medianvals_by_zip.txt` would be:

    C00177436|30004|384|1|384
    C00384818|02895|250|1|250
    C00177436|30750|230|1|230
    C00177436|04105|384|1|384
    C00384818|02895|292|2|583
    C00177436|04105|384|2|768

If we drop the zip code, there are four records with the same recipient, `C00177436`, and date of `01312017`. Their total amount of contributions is $1,382. 

For the recipient, `C00384818`, there are two records with the date `01122017` and total contribution of $583 and median of $292.

As a result, `medianvals_by_date.txt` would contain these lines in this order:

    C00177436|01312017|384|4|1382
    C00384818|01122017|292|2|583

## Repo directory structure

The directory structure for your repo should look like this:

    ├── README.md 
    ├── run.sh
    ├── src
    │   └── find_political_donors.py
    ├── input
    │   └── itcont.txt
    ├── output
    |   └── medianvals_by_zip.txt
    |   └── medianvals_by_date.txt
    ├── insight_testsuite
        └── run_tests.sh
        └── tests
            └── test_1
            |   ├── input
            |   │   └── itcont.txt
            |   |__ output
            |   │   └── medianvals_by_zip.txt
            |   |__ └── medianvals_by_date.txt
            ├── your-own-test
                ├── input
                │   └── your-own-input.txt
                |── output
                    └── medianvals_by_zip.txt
                    └── medianvals_by_date.txt
