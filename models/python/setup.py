#
# Copyright (c) 2023 Airbyte, Inc., all rights reserved.
#
import pip
pip.main(['install', 'python-dotenv'])

import os
import pathlib

from setuptools import setup
from dotenv import load_dotenv



# The directory containing this file
HERE = pathlib.Path(__file__).parent.parent.parent



print(sorted(HERE.iterdir()))

# The text of the README file
README = (HERE / "readme.md").read_text()

load_dotenv(HERE / ".env")

VERSION = os.getenv("VERSION") # use version declared in top level .env file

setup(
    name='airbyte_types',
    version=VERSION,
    description="Declares common types used across airbyte projects.",
    long_description=README,
    long_description_content_type="text/markdown",
    author="Airbyte",
    author_email="contact@airbyte.io",
    license="MIT",
    url="https://github.com/airbytehq/airbyte-types",
    classifiers=[
        # This information is used when browsing on PyPi.
        # Dev Status
        "Development Status :: 3 - Alpha",
        # Project Audience
        "Intended Audience :: Developers",
        "Topic :: Scientific/Engineering",
        "Topic :: Software Development :: Libraries :: Python Modules",
        "License :: OSI Approved :: MIT License",
        # Python Version Support
        "Programming Language :: Python :: 3.9",
        "Programming Language :: Python :: 3.8",
    ],
    keywords="airbyte airbyte-types",
    project_urls={
        "Documentation": "https://docs.airbyte.io/",
        "Source": "https://github.com/airbytehq/airbyte-types",
        "Tracker": "https://github.com/airbytehq/airbyte-types/issues",
    },
    packages=['airbyte_types.models'],
    setup_requires=['python-dotenv'],
    install_requires=[
        "pydantic~=1.9.2",
    ],
    python_requires=">=3.8",
)
